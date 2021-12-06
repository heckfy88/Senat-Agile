package com.senat.command.executor

import com.senat.command.Command
import com.senat.service.message.SendBotMessageService
import com.senat.service.DiscussionResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ResultCommand(private val sendBotMessageService: SendBotMessageService): Command {

    @Autowired
    private lateinit var discussionResultService: DiscussionResultService

    override fun getCommand(): String = "/report"

    override fun execute(update: Update) {
        val message = update.message
        val chatId = message.chatId

        if(message.text.length > 6) {
            val date = message.text.substring(8)
            val response = discussionResultService.collectDiscussionResult(date)
            sendBotMessageService.sendMessage(
                chatId.toString(),
                response
            )
        } else {
            sendBotMessageService.sendMessage(
                chatId.toString(),
                "Для получения отчета необходимо ввести дату"
            )
        }
    }
}