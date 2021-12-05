package com.senat.service

import com.senat.command.CommandContainer
import com.senat.service.message.SendBotMessageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*

@Component
class SenatAgileBot : TelegramLongPollingBot() {

    private val commandPrefix = "/"

    @Autowired
    private lateinit var commandContainer: CommandContainer

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Value("\${telegram.token}")
    private val token: String = ""

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage() && update.message.hasText()) {
            val messageText = update.message.text
            if (update.message.isCommand) {
                val commandIdentifier = messageText
                    .split("\\s+".toRegex())[0]
                    .lowercase(Locale.getDefault())
                val command = commandContainer.retrieveCommand(commandIdentifier)
                command.execute(update)
            }
        }
    }

    companion object{
        private const val COMMAND_DELIMITER: String = "\\s"

        fun Update.getCommandParameters(): List<String> {
            val message = message.text.trim()
            val commandParameters = message.split(COMMAND_DELIMITER.toRegex())
            return  commandParameters.subList(1, commandParameters.size)
        }
    }
}
