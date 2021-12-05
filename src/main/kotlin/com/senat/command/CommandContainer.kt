package com.senat.command

import com.senat.command.executor.*

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
        this.add(context.getBean(ResponsibleCommand::class.java))
        this.add(context.getBean(VoteCommand::class.java))
        this.add(context.getBean(InitCommand::class.java))
        this.add(context.getBean(HelpCommand::class.java))
        this.add(context.getBean(DiscussionCommand::class.java))
    }

    fun add(command: Command) {
        commands[command.getCommand()] = command
    }

    fun retrieveCommand(commandIdentifier: String): Command {
        return commands[commandIdentifier] ?: unknownCommand
    }
}