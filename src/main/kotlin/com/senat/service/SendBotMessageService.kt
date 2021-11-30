package com.senat.service

interface SendBotMessageService {
    fun sendMessage(chatId: String?, message: String?)
}