package com.senat.command.executor

import com.senat.command.Command
import com.senat.service.message.SendBotMessageService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UnknownCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    private val startMessage = "Неизвестная команда"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(
            update.message.chatId.toString(),
            startMessage
        )
    }

    override fun getCommand(): String = ""
}