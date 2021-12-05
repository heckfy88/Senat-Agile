package com.senat.service.discussion

import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class IdeaService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var chatRepository: ChatRepository

    @Autowired
    lateinit var discussionRepository: DiscussionRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    fun sendIdea(update: Update) {
        val message = update.message
        val config = chatRepository.findById(message.chatId).get()
        val currentDiscussion: DiscussionDto

        if (config.idea) {
            currentDiscussion = discussionRepository
                .findFirstByChatIdOrderByIdDesc(message.chatId)

            val user = UserDto(
                userId = message.from.id.toString(),
                name = message.from.userName
            )
            userRepository.save(user)

            val idea = IdeaDto(
                body = message.text.substring(5), // нужно разобраться
                sender = user,
                discussion = currentDiscussion
            )
            currentDiscussion.addIdea(idea)
            discussionRepository.save(currentDiscussion)
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваша идея отправлена")
        } else {
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Предложение идей не активно")
        }
    }
}
