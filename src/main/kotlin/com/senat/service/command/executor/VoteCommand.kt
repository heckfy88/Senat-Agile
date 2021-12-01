package com.senat.service.command.executor

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.service.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.objects.Update

class VoteCommand(sendBotMessageService: SendBotMessageService) : Command {

    @Autowired
    lateinit var ideaRepository: IdeaRepository

    override fun execute(update: Update) {
        val message = update.message

        val ideaVotes: IdeaDto = ideaRepository.findById(message.text.substring(6).toLong()).get()
        ideaVotes.votes++
        ideaRepository.save(ideaVotes)


    }
}
