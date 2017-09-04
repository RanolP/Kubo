package io.github.ranolp.kubo.telegram.bot.event

import io.github.ranolp.kubo.general.event.HearEvent
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.bot.objects.TelegramMessage

class TelegramBotHearEvent(message: TelegramMessage) : HearEvent(Telegram.BOT_SIDE, message){}