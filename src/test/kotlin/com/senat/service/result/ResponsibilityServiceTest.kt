package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.discussion.ResponsibilityService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import java.util.*

@ExtendWith(MockitoExtension::class)
class ResponsibilityServiceTest {
    @Mock
    lateinit var ideaRepository: IdeaRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var update: Update

    @Mock
    lateinit var message: Message

    @InjectMocks
    lateinit var responsibilityService: ResponsibilityService

    @Test
    fun `test should set a responsible person for an idea`() {
        whenever(ideaRepository.findById(any()))
            .thenReturn(Optional.of(idea))
        whenever(message.entities)
            .thenReturn(listOf(messageEntity))
        whenever(userRepository.findByName("name"))
            .thenReturn(user)
        whenever(ideaRepository.save(idea))
            .thenReturn(expectedIdea)
        whenever(update.message)
            .thenReturn(message)
        whenever(update.message.text)
            .thenReturn("/responsible 1 @name")
        val expected = IdeaDto(
            id = 1,
            body = "Поставить кофемашину в кабинет!",
            votes = 8,
            sender = UserDto(name = "Петр"),
            responsible = user,
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
        val actual = responsibilityService.setResponsible(update)
        assertEquals(expected, actual)
    }

    companion object {
        val idea = IdeaDto(
            id = 1,
            body = "Поставить кофемашину в кабинет!",
            votes = 8,
            sender = UserDto(name = "Петр"),
            responsible = null,
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
        val user = UserDto(
            userId = "1",
            name = "name"
        )
        val expectedIdea = IdeaDto(
            id = 1,
            body = "Поставить кофемашину в кабинет!",
            votes = 8,
            sender = UserDto(name = "Петр"),
            responsible = user,
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
        val messageEntity =
            MessageEntity("mention", 0, 0, null,
                User(1, "", false, "", "name", "", true, true, true),
                null, "@name")
    }
}
