package com.senat.service

import com.senat.service.command.CommandContainer
import com.senat.service.service.message.SendBotMessageServiceImpl
import com.senat.service.service.responsibility.ResponsibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*
import javax.annotation.PostConstruct

@Component
class Bot : TelegramLongPollingBot() {

    private val commandPrefix = "/"

    @Autowired
    private lateinit var responsibilityService: ResponsibilityService

    private lateinit var commandContainer: CommandContainer

    @PostConstruct
    fun init() {
        commandContainer = CommandContainer(
            SendBotMessageServiceImpl(this),
            responsibilityService
        )
    }

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Value("\${telegram.token}")
    private val token: String = ""

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = botName

    override fun onUpdateReceived(update: Update?) {
        if (update!!.hasMessage()) {
            val messageText = update.message.text
            if (messageText.startsWith(commandPrefix)) {
                val commandIdentifier = messageText
                    .split("\\s+".toRegex())[0]
                    .lowercase(Locale.getDefault())
                val command = commandContainer.retrieveCommand(commandIdentifier)
                command.execute(update)
            }
        }
    }
}