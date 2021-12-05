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
        val discussion = discussionRepository.findByDate(date)
        message
            .append("${discussion.title}\n")
            .append("${discussion.date}\n")
            .append(collectSummaryVoting(discussion))
        return message.toString()
    }

    companion object {
        fun formVotingMessage(idea: IdeaDto): String {
            val ideaMessage = idea.message
            val ideaSender = idea.sender.name
            val ideaVotes = idea.votes

            return "$ideaMessage ➖ $ideaVotes \uD83D\uDD25 \nПредложил(а): $ideaSender\n"
        }
    }
}