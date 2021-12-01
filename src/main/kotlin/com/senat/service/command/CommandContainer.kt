package com.senat.service.command

import com.senat.service.command.executor.Command
import com.senat.service.command.executor.ResponsibleCommand
import com.senat.service.command.executor.StartCommand
import com.senat.service.command.executor.UnknownCommand
import com.senat.service.service.message.SendBotMessageService

class CommandContainer(sendBotMessageService: SendBotMessageService) {

    private val commands: Map<String, Command> = hashMapOf(
        CommandName.START.commandName to StartCommand(sendBotMessageService),
        CommandName.RESPONSIBLE.commandName to ResponsibleCommand(sendBotMessageService)
    )

    private val unknownCommand = UnknownCommand(sendBotMessageService)

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}