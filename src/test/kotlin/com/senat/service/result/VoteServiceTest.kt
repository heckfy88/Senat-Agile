package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.ChatDto
import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.ChatRepository
import com.senat.repository.IdeaRepository
import com.senat.service.discussion.VoteService
import com.senat.service.message.SendBotMessageService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*

@ExtendWith(MockitoExtension::class)
class VoteServiceTest {
    @Mock
    lateinit var ideaRepository: IdeaRepository

    @Mock
    lateinit var chatRepository: ChatRepository

    @Mock
    lateinit var sendBotMessageService: SendBotMessageService

    @Mock
    lateinit var update: Update

    @Mock
    lateinit var message: Message

    @InjectMocks
    lateinit var voteService: VoteService

    @Test
    fun `vote() should increment votes on an idea`() {
        whenever(chatRepository.findById(any()))
            .thenReturn(Optional.of(chat))
        whenever(ideaRepository.findById(any()))
            .thenReturn(Optional.of(idea))
        whenever(update.message)
            .thenReturn(message)
        whenever(update.message.chatId)
            .thenReturn(1)
        whenever(update.message.text)
            .thenReturn("/vote 1")

        voteService.vote(update)

        assertEquals(1, idea.votes)
    }
    companion object {
        val chat = ChatDto(
            chat_id = 1,
            vote = true
        )
        val idea = IdeaDto(
            ideaId = 1,
            message = "Поставить кофемашину в кабинет!",
            votes = 0,
            sender = UserDto(name = "Петр"),
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
    }
}