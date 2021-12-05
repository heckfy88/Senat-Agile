package com.senat.command.executor

import com.senat.command.Command
import com.senat.service.discussion.IdeaService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class IdeaCommand(private val ideaService: IdeaService): Command {

    override fun execute(update: Update) = ideaService.sendIdea(update)

    override fun getCommand(): String = "/idea"


}
