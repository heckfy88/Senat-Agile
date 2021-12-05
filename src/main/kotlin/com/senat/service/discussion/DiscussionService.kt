package com.senat.service.discussion

import com.senat.dto.DiscussionDto
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.repository.IdeaRepository
import com.senat.service.discussion.IdeaService.Companion.getCommandParameters
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
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    @Autowired
    private lateinit var chatRepository: ChatRepository

    fun createDiscussion(update: Update): DiscussionDto? {

        var result: DiscussionDto? = null

        val config = chatRepository.findById(update.message.chatId).get()

        val commandParameters = update.getCommandParameters()

        if (!config.discussion) {
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Уже идет обсуждение!")
        } else {
            val date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now())
            val discussionText = commandParameters[0]
            result = discussionRepository.save(DiscussionDto(title = discussionText, date = date, chatId = update.message.chatId))
            config.discussion = false
            config.idea = true
            chatRepository.save(config)
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Обсуждение началось, тема: $discussionText, предлагайте свои идеи!")
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
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Прием идей окончен, начато голосование!")
            votingTimer(update)
        }, 20, TimeUnit.SECONDS)
    }

    fun votingTimer(update: Update) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val config = chatRepository.findById(update.message.chatId).get()
            config.vote = false
            config.discussion = true
            chatRepository.save(config)
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Голосование окончено!")
        }, 20, TimeUnit.SECONDS)
    }

    companion object{
        private const val COMMAND_DELIMITER: String = "\\s"

        fun Update.getCommandParameters(): List<String> {
            val message = message.text.trim()
            val commandParameters = message.split(COMMAND_DELIMITER.toRegex())
            return  commandParameters.subList(1, commandParameters.size)
        }
    }
}