package com.senat.service.message

import com.senat.service.SenatAgileBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

@Service
class SendBotMessageServiceImpl @Autowired constructor(
    private val senatAgileBot: SenatAgileBot
) : SendBotMessageService {

    override fun sendMessage(chatId: String, message: String) {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = message

        try {
            senatAgileBot.execute(sendMessage)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}