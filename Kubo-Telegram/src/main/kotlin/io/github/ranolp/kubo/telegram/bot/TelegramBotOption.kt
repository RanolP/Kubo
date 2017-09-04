package io.github.ranolp.kubo.telegram.bot

import io.github.ranolp.kubo.general.Option
import io.github.ranolp.kubo.telegram.Telegram

open class TelegramBotOption(val botName: String, val token: String, val hearerType: HearerType = HearerType.LONG_POLLING) : Option(Telegram.BOT_SIDE) {}