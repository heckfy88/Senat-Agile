package com.senat.service

import com.senat.dto.UserDto
import com.senat.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class InitService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun initializeUsers(update: Update) {
        val user = update.message.from
        val userId = user.id
        val userName = user.userName
        userRepository.save(
            UserDto(
                userId.toString(),
                userName
            )
        )
    }
}
