package com.senat.service.vote

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class VoteServiceImpl: VoteService {

    @Autowired
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService



    override fun vote(update: Update) {
        val message = update.message

        val canVote = userRepository.findById(message.from.id).get().permissions.vote

        val ideaExists = ideaRepository.existsById(message.text.substring(6, 8).toLong())

        if (ideaExists) {
            if (canVote) {

                val votePermission = userRepository.findById(message.from.id).get()
                votePermission.permissions.idea = false
                votePermission.permissions.vote = false
                userRepository.save(votePermission)

                val ideaVotes: IdeaDto = ideaRepository.findById(message.text.substring(6).toLong()).get()
                ideaVotes.votes++
                ideaRepository.save(ideaVotes)

                sendBotMessageService.sendMessage(update.message.chatId.toString(), "Ваш голос учтен")

            } else {
                sendBotMessageService.sendMessage(update.message.chatId.toString(), "Вы уже проголосовали")
            }
        } else {
            sendBotMessageService.sendMessage(update.message.chatId.toString(), "Такой идеи нет")
        }
    }


}