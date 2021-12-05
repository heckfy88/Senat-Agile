package com.senat.service.result

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.UserDto
import com.senat.repository.UserRepository
import com.senat.service.InitService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User

@ExtendWith(MockitoExtension::class)
class InitServiceTest {
    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var update: Update

    @Mock
    lateinit var message: Message

    @InjectMocks
    lateinit var initService: InitService

    @Test
    fun `should initialize users`() {
        whenever(update.message)
            .thenReturn(message)
        whenever(update.message.from)
            .thenReturn(User(1, "", false, "", "name", "", true, true, true))
        initService.initializeUsers(update)
        verify(userRepository, times(1)).save(user)
    }
    companion object {
        val user = UserDto(
            userId = "1",
            name = "name"
        )
    }
}