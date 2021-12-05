package com.senat.service.discussion

import com.senat.dto.IdeaDto
import com.senat.dto.UserDto
import com.senat.repository.IdeaRepository
import com.senat.repository.ChatRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class VoteService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var ideaRepository: IdeaRepository

    @Autowired
    private lateinit var chatRepository: ChatRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    fun vote(update: Update) {

        val message = update.message

        val config = chatRepository.findById(message.chatId).orElse(null)

        if (config.vote) {
            val votingUserId = message.from.id.toString()
            val idea = ideaRepository.findById(message.text.substring(6).toLong()).orElse(null)
            var user = userRepository.findByUserId(votingUserId).orElse(null)

            if (user == null) {
                val userSaved = UserDto(
                    userId = message.from.id.toString(),
                    name = message.from.userName
                )
                userRepository.save(userSaved)
                user = userSaved
            }
            if(idea == null) {
                sendBotMessageService.sendMessage(
                    update.message.chatId.toString(),
                    "Невозможно проголосовать за несуществующую идею"
                )
            } else {
                if (idea.votedUsers.contains(user)) {
                    sendBotMessageService.sendMessage(
                        update.message.chatId.toString(),
                        "Невозможно повтроное голосование за одну идею"
                    )
                } else {
                    idea.votedUsers.add(user)
                    idea.votes++
                    ideaRepository.save(idea)
                    sendBotMessageService.sendMessage(
                        update.message.chatId.toString(),
                        "Ваш голос учтен")
                }
            }
        } else {
            sendBotMessageService.sendMessage(
                update.message.chatId.toString(),
                "Голосование не активно")
        }

    }


}