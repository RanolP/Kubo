package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.general.side.Sided

open class Option(override val side: Side) : Sided {}