package com.senat.service.discussion

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
interface DiscussionService {

    fun startDiscussion (update: Update) {}
}