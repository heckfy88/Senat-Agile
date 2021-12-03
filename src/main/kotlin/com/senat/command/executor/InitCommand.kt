package com.senat.command.executor

import com.senat.service.init.InitService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class InitCommand(private var initService: InitService) : Command {

    override fun execute(update: Update) {
        initService.initializeUsers(update)
    }

    override fun getCommand() = "/hi"
}
