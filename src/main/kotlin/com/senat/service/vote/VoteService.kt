package com.senat.service.vote

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
interface VoteService {
    fun vote(update: Update) {}
}