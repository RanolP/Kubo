package io.github.ranolp.kubo.telegram

import io.github.ranolp.kubo.Adapters
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.bot.TelegramBotAdapter
import io.github.ranolp.kubo.telegram.client.TelegramClientAdapter


object Telegram {
    val BOT_SIDE = Side("Telegram", true, false)
    val CLIENT_SIDE = Side("Telegram", false, true)

    fun getBotAdapter() = Adapters.getTypedAdapter<TelegramBotAdapter>(BOT_SIDE)!!

    fun getClientAdapter() = Adapters.getTypedAdapter<TelegramClientAdapter>(CLIENT_SIDE)!!
}