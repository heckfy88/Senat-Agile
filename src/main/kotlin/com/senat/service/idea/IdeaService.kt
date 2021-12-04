package com.senat.service.idea

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
interface IdeaService {

    fun sendIdea(update: Update) {}
}