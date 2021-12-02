package com.senat.service.message

interface SendBotMessageService {
    fun sendMessage(chatId: String, message: String)
}