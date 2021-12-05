package com.senat.command

import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun execute(update: Update)
    fun getCommand(): String
}