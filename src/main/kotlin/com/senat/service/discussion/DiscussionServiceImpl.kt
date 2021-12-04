package com.senat.service.discussion

import com.senat.dto.DiscussionDto
import com.senat.repository.DiscussionRepository
import com.senat.repository.UserRepository
import com.senat.service.message.SendBotMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class DiscussionServiceImpl: DiscussionService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var discussionRepository: DiscussionRepository

    @Autowired
    private lateinit var sendBotMessageService: SendBotMessageService

    override fun startDiscussion(update: Update) {
        val message = update.message

        val title = update.message.text.substring(5)

        // val canStartDiscussions = userRepository.findById(message.from.id).get().permissions.discussion ?: false

        if (userRepository.existsById(message.from.id)) {
            discussionRepository.save(
                DiscussionDto(
                    discussionStarted = true,
                    title = title
                )
            )
            sendBotMessageService
                .sendMessage(update.message.chatId.toString(), "Создана беседа с темой: $title")
        } else {
            sendBotMessageService
                .sendMessage(update.message.chatId.toString(), "Не админ")
        }
    }
}