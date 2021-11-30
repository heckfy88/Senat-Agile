package com.senat.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


@Service
class SendBotMessageImpl:SendBotMessageService {

    @Autowired
    lateinit var bot: Bot

    override fun sendMessage(chatId: String?, message: String?) {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId!!
        sendMessage.enableHtml(true)
        sendMessage.text = message!!
        bot.execute(sendMessage)
    }
}
