package io.github.ranolp.kubo.general.event

import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.general.side.Sided

abstract class Event(override val side: Side) : Sided {}