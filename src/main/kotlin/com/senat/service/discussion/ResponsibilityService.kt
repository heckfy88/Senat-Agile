package com.senat.service.discussion

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
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

    fun setResponsible(update: Update) {
        val idea: IdeaDto?
        val ideaId: Long
        val chatId = update.message.chatId.toString()
        var user: UserDto? = null
        val commandParameters = update.message.text.trim()
            .split("\\s+".toRegex())

        update.message.entities.forEach {
            if (it.type == "mention") {
                user = UserDto(userId = it.text)
                userRepository.save(user!!)
            } else if (it.type == "text_mention") {
                user = UserDto(userId = it.user.id.toString())
                userRepository.save(user!!)
            }
        }

        ideaId = commandParameters[1].toLong()
        idea = ideaRepository.findByIdOrNull(ideaId)
        if (idea != null) {
            idea.responsible = user
            ideaRepository.save(idea)
            sendBotMessageService.sendMessage(chatId, "Ответственный за идею ${idea.id}: ${ideaRepository.findById(ideaId).get().responsible!!.userId}")
        } else {
            sendBotMessageService.sendMessage(chatId, "Идеи с таким ID не существует")
        }
    }
}
