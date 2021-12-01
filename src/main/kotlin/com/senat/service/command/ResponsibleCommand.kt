package com.senat.service.command

import com.senat.service.service.SendBotMessageService
import org.telegram.telegrambots.meta.api.objects.Update

class ResponsibleCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    private val startMessage = "Строка с ответственным"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }
}