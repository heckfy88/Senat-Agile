package com.senat.service.idea

import org.telegram.telegrambots.meta.api.objects.Update


interface IdeaService {
    fun sendIdea(update: Update) {}
}