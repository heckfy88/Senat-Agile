package com.senat.command.executor

import com.senat.service.vote.VoteService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class VoteCommand(private val voteService: VoteService) : Command {

    override fun execute(update: Update) = voteService.vote(update)


    override fun getCommand(): String = "/vote"
}