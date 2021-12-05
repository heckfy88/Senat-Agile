package com.senat.service.discussion

import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.service.SenatAgileBot.Companion.getCommandParameters
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class IdeaService {

    @Autowired
    lateinit var ideaRepository: IdeaRepository

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

        val commandParameters = update.getCommandParameters()

        val currentDiscussion: DiscussionDto

        if (config.idea) {
            currentDiscussion = discussionRepository.findFirstByChatIdOrderByDiscussionIdDesc(message.chatId)

            val user = UserDto(
                userId = message.from.id.toString(),
                name = message.from.userName
            )
            userRepository.save(user)

            val idea = IdeaDto(
                message = commandParameters[0], // нужно разобраться, поменял на CommandParameters
                sender = user,
                discussion = currentDiscussion
            )
            ideaRepository.save(idea)
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваша идея отправлена")
        } else {
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Предложение идей не активно")
        }
    }
}
