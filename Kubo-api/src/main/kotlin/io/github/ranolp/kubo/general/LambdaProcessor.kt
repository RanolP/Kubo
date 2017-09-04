package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.event.Event

class LambdaProcessor<T: Event>(private val process: (T) -> Any?) : Processor<T>() {
    override fun execute(event: T) {
        process(event)
    }
}