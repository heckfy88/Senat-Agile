package com.senat.command.executor

import com.senat.repository.DiscussionRepository
import com.senat.service.discussion.DiscussionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class DiscussionCommand(private var discussionService: DiscussionService) : Command {
    @Autowired
    lateinit var discussionRepository: DiscussionRepository

    override fun execute(update: Update) {
        discussionService.createDiscussion(update)
    }

    override fun getCommand(): String {
        return "/discussion"
    }
}