package com.senat.service.discussion

import com.senat.dto.DiscussionDto
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.repository.IdeaRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class DiscussionService {
    @Autowired
    private lateinit var discussionRepository: DiscussionRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    @Autowired
    private lateinit var chatRepository: ChatRepository

    fun createDiscussion(update: Update): DiscussionDto? {
        var result: DiscussionDto? = null
        val config = chatRepository.findById(update.message.chatId).get()

        if (!config.discussion) {
            sendBotMessageService.sendMessage(
                update.message.chatId.toString(),
                "Уже идет обсуждение!"
            )
        } else {
            val message = update.message.text.trim()
            val date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now())
            val discussionText = message.substring(message.indexOf(" ") + 1)
            result = discussionRepository.save(
                DiscussionDto(
                    title = discussionText,
                    date = date,
                    chatId = update.message.chatId
                )
            )
            config.discussion = false
            config.idea = true
            chatRepository.save(config)
            sendBotMessageService.sendMessage(
                update.message.chatId.toString(),
                "Обсуждение началось, тема: $discussionText, предлагайте свои идеи!"
            )
        }
        if (result != null) ideasTimer(update)

        return result
    }

    fun ideasTimer(update: Update) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val config = chatRepository.findById(update.message.chatId).get()
            config.idea = false
            config.vote = true
            chatRepository.save(config)
            sendBotMessageService.sendMessage(
                update.message.chatId.toString(),
                "Прием идей окончен, начато голосование!"
            )
            discussionRepository.findFirstByChatIdOrderByIdDesc(update.message.chatId).ideas.forEach {
                sendBotMessageService.sendMessage(
                    update.message.chatId.toString(),
                    "Идея №${it.id} \n${it.body}"
                )
            }
            votingTimer(update)
        }, 15, TimeUnit.SECONDS)
    }

    fun votingTimer(update: Update) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val config = chatRepository.findById(update.message.chatId).get()
            config.vote = false
            config.responsible = true
            config.discussion = true
            chatRepository.save(config)
            sendBotMessageService.sendMessage(
                update.message.chatId.toString(),
                "Голосование окончено!"
            )
            discussionRepository.findFirstByChatIdOrderByIdDesc(update.message.chatId).ideas.forEach {
                sendBotMessageService.sendMessage(
                    update.message.chatId.toString(),
                    "Идея №${it.id} \n${it.body} \nГолоса: ${it.votes}"
                )
            }
        }, 30, TimeUnit.SECONDS)
    }
}