package com.senat.service.service.message

import org.springframework.stereotype.Service

@Service
interface SendBotMessageService {
    fun sendMessage(chatId: String, message: String)
}