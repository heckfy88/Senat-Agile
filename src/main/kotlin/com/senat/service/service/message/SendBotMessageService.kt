package com.senat.service.service.message

interface SendBotMessageService {
    fun sendMessage(chatId: String, message: String)
}