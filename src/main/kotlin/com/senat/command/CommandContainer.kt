package com.senat.command

import com.senat.command.executor.*
import com.senat.service.message.SendBotMessageService

class CommandContainer(sendBotMessageService: SendBotMessageService) {

    private val commands: Map<String, Command> = hashMapOf(
        CommandName.START.commandName to StartCommand(sendBotMessageService),
        CommandName.RESPONSIBLE.commandName to ResponsibleCommand(sendBotMessageService),
        CommandName.IDEA.commandName to IdeaCommand(sendBotMessageService),
        CommandName.VOTE.commandName to VoteCommand(sendBotMessageService)
    )

    private val unknownCommand = UnknownCommand(sendBotMessageService)

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}