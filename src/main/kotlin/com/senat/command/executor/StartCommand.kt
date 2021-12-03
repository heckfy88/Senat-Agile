package com.senat.command.executor

import com.senat.service.message.SendBotMessageService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class StartCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    private val startMessage = "Добро пожаловать"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }

    override fun getCommand(): String = "/start"
}