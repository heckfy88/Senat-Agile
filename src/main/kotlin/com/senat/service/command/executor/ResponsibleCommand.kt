package com.senat.service.command.executor

import com.senat.service.service.message.SendBotMessageService
import org.telegram.telegrambots.meta.api.objects.Update

class ResponsibleCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    private val startMessage = "Строка с ответственным"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }

    override fun getCommand(): String = "/responsible"
}