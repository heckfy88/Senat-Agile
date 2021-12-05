package com.senat.command.executor

import com.senat.command.Command
import com.senat.service.message.SendBotMessageService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class HelpCommand(private var sendBotMessageService: SendBotMessageService) : Command {

    private val instructions =
        "❓ Что я умею?\nпока не придумала\n\n" +
                "Команды:\n" +
                "✅ /start: приветственное сообщение\n" +
                "✅ /hi: дай знать, что ты хочешь принять участие в дискуссии\n" +
                "✅ /discussion {название дискуссии: создай дискуссию\n" +
                "✅ /idea {ваша идея}: напиши свою идею\n" +
                "✅ /vote {id идеи}: проголосуй за\n" +
                "✅ /responsible {id идеи} {@упомянутый пользователь}: назначь ответственного\n"

    override fun execute(update: Update) {
        sendBotMessageService.sendMessage(update.message.chatId.toString(), instructions)
    }

    override fun getCommand() = "/help"
}