package io.github.ranolp.kubo.general.event

import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.side.Side

abstract class HearEvent(side: Side, val message: Message) : Event(side){}