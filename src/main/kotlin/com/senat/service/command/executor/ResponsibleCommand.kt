package com.senat.service.command.executor

import com.senat.service.service.message.SendBotMessageService
import com.senat.service.service.responsibility.ResponsibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.objects.Update

class ResponsibleCommand(private val sendBotMessageService: SendBotMessageService) : Command {

    @Autowired
    private var responsibilityService: ResponsibilityService? = null

    private val startMessage = "Строка с ответственным"

    override fun execute(update: Update) {
        val commandParameters = update.message.text.trim()
            .split("\\s+".toRegex())
            .subList(1, 3)
        responsibilityService!!.setResponsible(commandParameters)
        sendBotMessageService.sendMessage(update.message.chatId.toString(), startMessage)
    }
}