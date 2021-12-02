package com.senat.service.service.result

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import org.springframework.stereotype.Service

@Service
class VotingResultService(private val ideaRepository: IdeaRepository) {

    fun collectSingleIdeaVoting(ideaId: Long): String {
        val idea = ideaRepository.findById(ideaId).get()
        return formVotingMessage(idea)
    }

    fun collectSummaryVoting(): String {
        val message = StringBuilder()
        val listOfIdeas = ideaRepository.findAllByOrderByVotesDesc()
        listOfIdeas.forEach {
            message
                .append(
                    formVotingMessage(it)
                )
        }
        return message.toString()
    }

    companion object {
        fun formVotingMessage(idea: IdeaDto): String {
            val ideaMessage = idea.message
            val ideaSender = idea.sender.name
            val ideaVotes = idea.votes

            return "$ideaMessage | $ideaVotes :fire: \nПредложил(а): $ideaSender"
        }
    }
}