package com.senat.command.executor

import com.senat.command.Command
import com.senat.dto.ChatDto
import com.senat.repository.ChatRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class StartCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    @Autowired
    private lateinit var chatRepository: ChatRepository

    private val startMessage = "Добро пожаловать"

    override fun execute(update: Update) {
        val chatId = update.message.chatId

        if (chatRepository.findByIdOrNull(chatId) == null) {
            chatRepository.save(ChatDto(chatId))
        }
        sendBotMessageService.sendMessage(
            update.message.chatId.toString(),
            startMessage
        )
    }

    override fun getCommand(): String = "/start"
}