package com.senat.command.executor

import com.senat.service.discussion.DiscussionService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class DiscussionCommand(private val discussionService: DiscussionService): Command {

    override fun execute(update: Update) = discussionService.startDiscussion(update)

    override fun getCommand(): String = "/discussion"
}