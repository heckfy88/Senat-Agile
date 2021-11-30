package com.senat.service

import com.senat.service.command.Command
import org.checkerframework.checker.units.qual.A
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.objects.Update


class Discussion() : Command {

    @Autowired
    lateinit var sendBotMessageService: SendBotMessageService

    override fun execute(update: Update?) {
        sendBotMessageService.sendMessage(update!!.message.chatId.toString(), "Hi, senators")
    }
}
