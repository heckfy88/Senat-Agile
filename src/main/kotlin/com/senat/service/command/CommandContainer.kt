package com.senat.service.command

import com.senat.service.command.executor.*
import com.senat.service.service.message.SendBotMessageService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CommandContainer(sendBotMessageService: SendBotMessageService) {

    @Autowired
    lateinit var context: ApplicationContext

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

    private val unknownCommand = UnknownCommand(sendBotMessageService)

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}