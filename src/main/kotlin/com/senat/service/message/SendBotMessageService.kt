package com.senat.service.message

import org.springframework.stereotype.Service

@Service
interface SendBotMessageService {
    fun sendMessage(chatId: String, message: String)
}