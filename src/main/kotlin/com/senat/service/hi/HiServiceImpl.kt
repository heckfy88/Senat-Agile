package com.senat.service.hi

import com.senat.dto.PermissionDto
import com.senat.dto.UserDto
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class HiServiceImpl: HiService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    override fun setAdmin(update: Update) {
        val message = update.message

        userRepository.save(
            UserDto(
                userId = message.from.id,
                name = message.from.firstName + " " + message.from.lastName,
                permissions = PermissionDto(
                    discussion = true,
                    responsible = true
                )
            )
        )

        sendBotMessageService.sendMessage(update.message.chatId.toString(), "Админ установлен")
    }

}