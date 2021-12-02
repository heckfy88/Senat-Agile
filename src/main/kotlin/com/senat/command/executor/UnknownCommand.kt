package com.senat.command.executor

import com.senat.service.message.SendBotMessageService
import org.telegram.telegrambots.meta.api.objects.Update

class UnknownCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    private val startMessage = "Неизвестная команда"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }
}