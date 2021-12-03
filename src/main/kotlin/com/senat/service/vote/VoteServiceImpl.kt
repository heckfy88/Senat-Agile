package com.senat.service.vote

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class VoteServiceImpl: VoteService {


    @Autowired
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    override fun vote(update: Update) {
        val message = update.message

        val ideaVotes: IdeaDto = ideaRepository.findById(message.text.substring(6).toLong()).get()
        ideaVotes.votes++
        ideaRepository.save(ideaVotes)

        sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваш голос учтен")

    }


}