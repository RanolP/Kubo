package io.github.ranolp.kubo.telegram.client.event

import io.github.ranolp.kubo.general.event.HearEvent
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.client.objects.TelegramClientMessage

class TelegramClientHearEvent(message: TelegramClientMessage) : HearEvent(Telegram.CLIENT_SIDE, message) {}