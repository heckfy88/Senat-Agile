package com.senat.command.executor

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class VoteCommand(sendBotMessageService: SendBotMessageService) : Command {

    @Autowired
    lateinit var ideaRepository: IdeaRepository

    override fun execute(update: Update) {
        val message = update.message

        val ideaVotes: IdeaDto = ideaRepository.findById(message.text.substring(6).toLong()).get()
        ideaVotes.votes++
        ideaRepository.save(ideaVotes)


    }

    override fun getCommand(): String = "/vote"
}