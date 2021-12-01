package com.senat.service.service

import com.senat.service.Bot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Service
class SendBotMessageServiceImpl @Autowired constructor(
    private val bot: Bot
) : SendBotMessageService {

    override fun sendMessage(chatId: String, message: String) {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = message

        try {
            bot.execute(sendMessage)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}