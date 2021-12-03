package com.senat.service.responsibility

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class ResponsibilityService {
    @Autowired
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    fun setResponsible(update: Update): IdeaDto? {
        lateinit var user: UserDto
        lateinit var idea: IdeaDto
        var ideaId: Long = 0
        var userId: Long = 0
        val chatId = update.message.chatId.toString()
        val commandParameters = update.message.text.trim()
            .split("\\s+".toRegex())
        if (commandParameters.size != 3) {
            sendBotMessageService.sendMessage(chatId, "Неверное количество аргументов")
            return null
        }
        try {
            ideaId = commandParameters[1].toLong()
            userId = commandParameters[2].toLong()
        } catch (e: NumberFormatException) {
            sendBotMessageService.sendMessage(chatId, "Аргументы должны быть цифрами")
            return null
        }
        try {
            user = userRepository.findById(userId).get()
        } catch (e: NoSuchElementException) {
            sendBotMessageService.sendMessage(chatId, "Пользователь с данным id не существует")
            return null
        }
        try {
            idea = ideaRepository.findById(ideaId).get()
        } catch (e: NoSuchElementException) {
            sendBotMessageService.sendMessage(chatId, "Идея с данным id не существует")
            return null
        }
        idea.responsible = user
        return ideaRepository.save(idea)
    }
}
