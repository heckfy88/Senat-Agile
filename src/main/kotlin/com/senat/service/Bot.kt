package com.senat.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class Bot : TelegramLongPollingBot() {

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Value("\${telegram.token}")
    private val token: String = ""

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            if (message.hasText()) {
                if (message.text == "/start")
                execute(SendMessage(chatId.toString(), "Добро пожаловать"))
                if (message.text == "/discussion")
                    Discussion()
            }
        }
    }
}