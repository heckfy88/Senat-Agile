package com.senat.command.executor

import com.senat.dto.ChatDto
import com.senat.repository.ChatRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class StartCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    @Autowired
    private lateinit var chatRepository: ChatRepository

    private val startMessage = "Добро пожаловать"

    override fun execute(update: Update) {
        chatRepository.save(ChatDto(update.message.chatId))
        println("Чат сохранен: ${update.message.chatId}")
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }

    override fun getCommand(): String = "/start"
}