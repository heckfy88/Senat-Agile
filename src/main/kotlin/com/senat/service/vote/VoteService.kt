package com.senat.service.vote

import org.telegram.telegrambots.meta.api.objects.Update

interface VoteService {
    fun vote(update: Update) {}
}