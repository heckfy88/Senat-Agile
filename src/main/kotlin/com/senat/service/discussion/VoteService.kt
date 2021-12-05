package com.senat.service.discussion

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.repository.ChatRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class VoteService {

    @Autowired
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    private lateinit var chatRepository: ChatRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    fun vote(update: Update) {

        val message = update.message

        val config = chatRepository.findById(message.chatId).get()

        if (config.vote) {
            val ideaVotes: IdeaDto = ideaRepository.findById(message.text.substring(6).toLong()).get()
            ideaVotes.votes++
            ideaRepository.save(ideaVotes)

            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваш голос учтен")
        } else {
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Голосование не активно")
        }

    }


}