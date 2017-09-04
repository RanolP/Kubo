package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.event.Event

abstract class Processor<T : Event> {
    operator fun invoke(event: T) {
        execute(event)
    }

    abstract fun execute(event: T)
}