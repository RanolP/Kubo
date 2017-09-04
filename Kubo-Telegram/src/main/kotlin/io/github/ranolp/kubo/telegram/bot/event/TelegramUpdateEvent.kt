package io.github.ranolp.kubo.telegram.bot.event

import io.github.ranolp.kubo.general.event.Event
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.bot.objects.Update

class TelegramUpdateEvent(val update: Update) : Event(Telegram.BOT_SIDE) {}