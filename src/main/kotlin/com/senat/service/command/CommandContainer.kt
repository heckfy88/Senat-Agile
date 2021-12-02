package com.senat.service.command

import com.senat.service.command.executor.Command
import com.senat.service.command.executor.ResponsibleCommand
import com.senat.service.command.executor.StartCommand
import com.senat.service.command.executor.UnknownCommand
import com.senat.service.service.message.SendBotMessageService
import com.senat.service.service.responsibility.ResponsibilityService

class CommandContainer(sendBotMessageService: SendBotMessageService,
                       responsibilityService: ResponsibilityService) {

    private val commands: Map<String, Command> = hashMapOf(
        CommandName.START.commandName to StartCommand(sendBotMessageService),
        CommandName.RESPONSIBLE.commandName to ResponsibleCommand(responsibilityService)
    )

    private val unknownCommand = UnknownCommand(sendBotMessageService)

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}