package com.senat.service.command.executor

import com.senat.service.service.message.SendBotMessageService
import com.senat.service.service.responsibility.ResponsibilityService
import org.telegram.telegrambots.meta.api.objects.Update

class ResponsibleCommand(private var responsibilityService: ResponsibilityService) : Command {

//    private val startMessage = "Строка с ответственным"

    override fun execute(update: Update) {
        val commandParameters = update.message.text.trim()
            .split("\\s+".toRegex())
            .subList(1, 3)
        responsibilityService.setResponsible(commandParameters)
//        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }
}