package com.senat.service

import com.senat.dto.DiscussionDto
import com.senat.dto.IdeaDto
import com.senat.repository.DiscussionRepository
import com.senat.repository.IdeaRepository
import org.springframework.stereotype.Service

@Service
class DiscussionResultService(
    private val ideaRepository: IdeaRepository,
    private val discussionRepository: DiscussionRepository
    ) {

    fun collectSummaryVoting(discussion: DiscussionDto): String {
        val message = StringBuilder()
        val listOfIdeas = ideaRepository.findAllByDiscussionOrderByVotesDesc(discussion)
        listOfIdeas.forEach {
            message
                .append(
                    formVotingMessage(it)
                )
        }
        return message.toString()
    }

    fun collectDiscussionResult(date: String): String {
        val message = StringBuilder()
        discussionRepository.findAllByDate(date).forEach {
            message
                .append("${it.title}\n")
                .append("${it.date}\n")
                .append(collectSummaryVoting(it))
        }
        return message.toString()
    }

    companion object {
        fun formVotingMessage(idea: IdeaDto): String {
            val ideaMessage = idea.body
            val ideaSender = idea.sender.name
            val ideaVotes = idea.votes
            val ideaResponsible = idea.responsible?.userId

            return "$ideaMessage ➖ $ideaVotes \uD83D\uDD25 \nПредложил(а): $ideaSender\nОтветственный(ая): $ideaResponsible\n"
        }
    }
}