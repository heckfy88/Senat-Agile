package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.DiscussionRepository
import com.senat.repository.IdeaRepository
import com.senat.service.DiscussionResultService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExtendWith(MockitoExtension::class)
class DiscussionResultServiceTest {

    @Mock
    lateinit var ideaRepository: IdeaRepository

    @Mock
    lateinit var discussionRepository: DiscussionRepository

    @InjectMocks
    lateinit var discussionResultService: DiscussionResultService

    @ParameterizedTest
    @ValueSource(
        strings = [
            "idea1",
            "idea2",
            "idea3"
        ]
    )
    fun`test should return correct summary voting results`(suite: String) {
        val executedSuite = suits[suite] ?: throw IllegalArgumentException("not supported here")
        val idea = executedSuite.idea

        whenever(ideaRepository.findAllByDiscussionOrderByVotesDesc(any()))
            .thenReturn(listOf(idea))

        val expected = formVotingMessage(idea)
        val actual = discussionResultService.collectSummaryVoting(discussion)

        assertEquals(expected, actual)
    }

    @Test
    fun `test should return correct discussion result`() {
        whenever(discussionRepository.findByDate(any()))
            .thenReturn(discussion)

        val expected = "The best discussion\n" + DateTimeFormatter
            .ofPattern("yyyy/MM/dd").format(LocalDate.now()) + "\n"
        val actual = discussionResultService.collectDiscussionResult(DateTimeFormatter
            .ofPattern("yyyy/MM/dd").format(LocalDate.now()))

        assertEquals(expected, actual)
    }

    data class Suite(
        val idea: IdeaDto
    )

    companion object {
        val idea1 = IdeaDto(
            ideaId = 0,
            message = "Поставить кофемашину в кабинет!",
            votes = 8,
            sender = UserDto(name = "Петр"),
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
        val idea2 = IdeaDto(
            ideaId = 0,
            message = "Надоело начальство, давайте поменяем его!",
            votes = 2,
            sender = UserDto(name = "Иван"),
            discussion = DiscussionDto(title = "some", chatId = 1)
        )
        val idea3 = IdeaDto(
            ideaId = 0,
            message = "Хочу котиков на рабочем месте...",
            votes = 5,
            sender = UserDto(name = "Олеся"),
            discussion = DiscussionDto(title = "some", chatId = 1)
        )

        val ideas = listOf(idea1, idea2, idea3)

        val suits = mapOf(
            "idea1" to Suite(idea1),
            "idea2" to Suite(idea2),
            "idea3" to Suite(idea3)
        )

        val discussion = DiscussionDto(
            chatId = 1,
            title = "The best discussion",
            date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now())
        )

        fun formVotingMessage(idea: IdeaDto): String {
            val ideaMessage = idea.message
            val ideaSender = idea.sender.name
            val ideaVotes = idea.votes

            return "$ideaMessage ➖ $ideaVotes \uD83D\uDD25 \nПредложил(а): $ideaSender\n"
        }
    }
}
