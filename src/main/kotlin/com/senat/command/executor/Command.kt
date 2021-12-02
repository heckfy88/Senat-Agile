package com.senat.command.executor

import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun execute(update: Update)
}