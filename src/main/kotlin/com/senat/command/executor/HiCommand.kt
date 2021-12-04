package com.senat.command.executor

import com.senat.service.hi.HiService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class HiCommand(private val hiService: HiService): Command {

    override fun execute(update: Update) = hiService.setAdmin(update)

    override fun getCommand(): String = "/hi"
}