package com.senat.command.executor

import com.senat.command.Command
import com.senat.service.discussion.ResponsibilityService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ResponsibleCommand(private var responsibilityService: ResponsibilityService) : Command {

    override fun execute(update: Update) {
        responsibilityService.setResponsible(update)
    }

    override fun getCommand() = "/responsible"
}
