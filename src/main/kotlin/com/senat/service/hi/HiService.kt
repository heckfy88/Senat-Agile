package com.senat.service.hi

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
interface HiService {

    fun setAdmin(update: Update) {}
}