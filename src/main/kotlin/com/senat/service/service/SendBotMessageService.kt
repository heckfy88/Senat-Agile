package com.senat.service.service

interface SendBotMessageService {
    fun sendMessage(chatId: String, message: String)
}