package com.senat.command.executor

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import org.telegram.telegrambots.meta.api.objects.Update

@Component
class IdeaCommand(private val sendBotMessageService: SendBotMessageService): Command {


    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var ideaRepository: IdeaRepository

    override fun execute(update: Update) {
        val message = update.message
        val chatId = message.chatId
        val user = UserDto(
            userId = message.from.id,
            name = message.from.userName
        )
        userRepository.save(user)
        val idea = IdeaDto(
            message = message.text.substring(5),
            sender = user
        )
        ideaRepository.save(idea)
        sendBotMessageService.sendMessage(chatId.toString(), message.from.id.toString())
    }
}
