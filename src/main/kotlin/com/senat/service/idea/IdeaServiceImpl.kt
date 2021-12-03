package com.senat.service.idea

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class IdeaServiceImpl: IdeaService {

    @Autowired
    lateinit var ideaRepository: IdeaRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService


    override fun sendIdea(update: Update) {
        val message = update.message

        val user = UserDto(
            userId = message.from.id,
            name = message.from.userName
        )
        userRepository.save(user)
        val idea = IdeaDto(
            message = message.text.substring(5),
            sender = user
        )
        ideaRepository.save(idea)

        sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваша идея отправлена")

    }
}
