package io.github.ranolp.kubo.telegram

import io.github.ranolp.kubo.Adapters
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.bot.TelegramBotAdapter


object Telegram {
    val BOT_SIDE = Side("Telegram", true, false)

    fun getAdapter() = Adapters.getTypedAdapter<TelegramBotAdapter>(BOT_SIDE)!!
}