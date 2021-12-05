package com.senat.command.executor

import com.senat.command.Command
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import com.senat.service.discussion.IdeaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class IdeaCommand(private val ideaService: IdeaService): Command {

    @Autowired
    lateinit var ideaRepository: IdeaRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override fun execute(update: Update) = ideaService.sendIdea(update)

    override fun getCommand(): String = "/idea"


}
