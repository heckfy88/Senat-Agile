package com.senat.service.result

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IllegalArgumentException
import java.util.*
/*

@ExtendWith(MockitoExtension::class)
class VotingResultServiceTest {

    @Mock
    lateinit var ideaRepository: IdeaRepository

    @InjectMocks
    lateinit var votingResultService: VotingResultService

    @ParameterizedTest
    @ValueSource(
        strings = [
            "idea1",
            "idea2",
            "idea3"
        ]
    )
    fun`test should return correct message for single idea`(suite: String) {
        val executedSuite = suits[suite] ?: throw IllegalArgumentException("not supported here")
        val idea = executedSuite.idea

        whenever(ideaRepository.findById(any()))
            .thenReturn(Optional.of(idea))

        val expected = formVotingMessage(idea)
        val actual = votingResultService.collectSingleIdeaVoting(idea.ideaId)

        assertEquals(expected, actual)
    }

    @Test
    fun `test should return correct rating ideas list`() {
        whenever(ideaRepository.findAllByOrderByVotesDesc())
            .thenReturn(ideas)

        val expected = formRatingMessage(ideas)
        val actual = votingResultService.collectSummaryVoting()

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
            UserDto(name = "Петр")
        )
        val idea2 = IdeaDto(
            ideaId = 0,
            message = "Надоело начальство, давайте поменяем его!",
            votes = 2,
            UserDto(name = "Иван")
        )
        val idea3 = IdeaDto(
            ideaId = 0,
            message = "Хочу котиков на рабочем месте...",
            votes = 5,
            UserDto(name = "Олеся")
        )

        val ideas = listOf(idea1, idea2, idea3)

        val suits = mapOf(
            "idea1" to Suite(idea1),
            "idea2" to Suite(idea2),
            "idea3" to Suite(idea3)
        )

        fun formVotingMessage(idea: IdeaDto): String {
            val ideaMessage = idea.message
            val ideaSender = idea.sender.name
            val ideaVotes = idea.votes

            return "$ideaMessage | $ideaVotes :fire: \nПредложил(а): $ideaSender"
        }

        fun formRatingMessage(ideas: List<IdeaDto>): String {
            val message = StringBuilder()
            ideas.forEach {
                message
                    .append(
                        formVotingMessage(it)
                    )
            }
            return message.toString()
        }
    }
}
*/
