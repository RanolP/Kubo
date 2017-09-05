package io.github.ranolp.kubo.telegram.bot.functions

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import io.github.ranolp.kubo.telegram.bot.objects.TelegramUser
import io.github.ranolp.kubo.telegram.errors.NotOkError

object getMe : TelegramFunction<TelegramUser>("getMe") {
    operator fun invoke() = request()
    override fun parse(request: Request, response: Response, result: String): TelegramUser {
        return work(result, ::TelegramUser) ?: throw NotOkError
    }
}