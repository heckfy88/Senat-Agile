package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.ChatDto
import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.discussion.IdeaService
import com.senat.service.message.SendBotMessageService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import java.util.*

@ExtendWith(MockitoExtension::class)
class IdeaServiceTest {
    @Mock
    lateinit var ideaRepository: IdeaRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var chatRepository: ChatRepository

    @Mock
    lateinit var discussionRepository: DiscussionRepository

    @Mock
    lateinit var sendBotMessageService: SendBotMessageService

    @Mock
    lateinit var update: Update

    @Mock
    lateinit var message: Message

    @InjectMocks
    lateinit var ideaService: IdeaService

    @Test
    fun `sendIdea() should save idea to db`() {
        whenever(update.message)
            .thenReturn(message)
        whenever(update.message.text)
            .thenReturn("/ideamy brilliant idea")
        whenever(update.message.from)
            .thenReturn(User(1, "", false, "", "name", "", true, true, true))
        whenever(chatRepository.findById(any()))
            .thenReturn(Optional.of(chat))
        whenever(discussionRepository.findFirstByChatIdOrderByIdDesc(any()))
            .thenReturn(discussion)
        whenever(userRepository.save(user))
            .thenReturn(user)
        whenever(ideaRepository.save(idea))
            .thenReturn(idea)

        ideaService.sendIdea(update)
        verify(chatRepository, times(1)).findById(any())
        verify(discussionRepository, times(1)).findFirstByChatIdOrderByIdDesc(any())
        verify(userRepository, times(1)).save(user)
        verify(ideaRepository, times(1)).save(idea)
    }

    companion object {
        val chat = ChatDto(
            chat_id = 1,
            idea = true
        )
        val discussion = DiscussionDto(
            chatId = 1,
            title = "The best discussion"
        )
        val user = UserDto(
            userId = "1",
            name = "name"
        )
        val idea = IdeaDto(
            id = 0,
            body = "my brilliant idea",
            votes = 0,
            sender = user,
            discussion = discussion
        )
    }
}