package com.senat.service.responsibility

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
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
        lateinit var idea: IdeaDto
        var ideaId: Long = 0
        val chatId = update.message.chatId.toString()
        val user: UserDto? = getUserMentioned(update.message)

        val commandParameters = update.message.text.trim()
            .split("\\s+".toRegex())
        if (commandParameters.size != 3) {
            sendBotMessageService.sendMessage(chatId, "Неверное количество аргументов")
            return null
        }

        try {
            ideaId = commandParameters[1].toLong()
            idea = ideaRepository.findById(ideaId).get()
        } catch (e: Exception) {
            sendBotMessageService.sendMessage(chatId, "Идея с данным id не существует")
            return null
        }

        if (user == null) {
            sendBotMessageService.sendMessage(chatId, "Пользователь не найден")
            return null
        }

        idea.responsible = user
        return ideaRepository.save(idea)
    }

    fun getUserMentioned(message: Message): UserDto? {
        // TODO: нужно, чтобы вначале все участники попали в бд!!!!!!
        for (item in message.entities) {
            if (item.type == "text_mention") {
                return UserDto(item.user.id)
            } else if (item.type == "mention") {
                return userRepository.findByName(item.text.substring(1))
            }
        }
        return null
    }
}
