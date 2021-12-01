package com.senat.service.command

import com.senat.service.service.SendBotMessageService

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