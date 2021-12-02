package com.senat.command

import com.senat.command.executor.Command
import com.senat.command.executor.IdeaCommand
import com.senat.command.executor.StartCommand
import com.senat.command.executor.UnknownCommand
import com.senat.service.message.SendBotMessageService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CommandContainer {

    @Autowired
    lateinit var context: ApplicationContext

    @Autowired
    lateinit var unknownCommand: UnknownCommand

    private val commands: MutableMap<String, Command> = mutableMapOf()

    @PostConstruct
    fun initIt() {
        this.add(context.getBean(StartCommand::class.java))
        this.add(context.getBean(IdeaCommand::class.java))
    }

    fun add(command: Command) {
        commands[command.getCommand()] = command
    }

//    private val commands: Map<String, Command> = hashMapOf(
//        CommandName.START.commandName to StartCommand(sendBotMessageService),
//        CommandName.RESPONSIBLE.commandName to ResponsibleCommand(sendBotMessageService),
//        CommandName.IDEA.commandName to IdeaCommand(sendBotMessageService),
//        CommandName.VOTE.commandName to VoteCommand(sendBotMessageService)
//    )

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}