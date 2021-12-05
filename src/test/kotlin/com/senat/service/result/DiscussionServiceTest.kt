package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.ChatDto
import com.senat.dto.DiscussionDto
import com.senat.repository.ChatRepository
import com.senat.repository.DiscussionRepository
import com.senat.service.discussion.DiscussionService
import com.senat.service.message.SendBotMessageService
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@ExtendWith(MockitoExtension::class)
class DiscussionServiceTest {
    @Mock
    lateinit var discussionRepository: DiscussionRepository

    @Mock
    lateinit var sendBotMessageService: SendBotMessageService

    @Mock
    lateinit var chatRepository: ChatRepository

    @Mock
    lateinit var update: Update

    @Mock
    lateinit var message: Message

    @InjectMocks
    lateinit var discussionService: DiscussionService

    @Test
    fun `createDiscussion should save discussion to db`() {
        whenever(update.message)
            .thenReturn(message)
        whenever(update.message.chatId)
            .thenReturn(1)
        whenever(update.message.text)
            .thenReturn("/discussion The best discussion")
        whenever(chatRepository.findById(any()))
            .thenReturn(Optional.of(chat))
        whenever(discussionRepository.save(discussion))
            .thenReturn(discussion)
        whenever(chatRepository.save(chat))
            .thenReturn(chat)
        discussionService.createDiscussion(update)
        assertTrue(chat.idea)
        assertFalse(chat.discussion)
    }
    companion object {
        val chat = ChatDto(
            chat_id = 1,
            discussion = true,
            idea = false
        )
        val discussion = DiscussionDto(
            chatId = 1,
            title = "The best discussion",
            date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now())
        )
    }
}