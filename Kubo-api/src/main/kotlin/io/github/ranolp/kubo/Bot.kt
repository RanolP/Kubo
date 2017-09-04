package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.LambdaProcessor
import io.github.ranolp.kubo.general.Processor
import io.github.ranolp.kubo.general.event.Event
import io.github.ranolp.kubo.general.event.Events

open class Bot {
    open fun start() {}

    protected inline fun <reified T : Event> watch(processor: Processor<T>) {
        Events.watch(T::class, processor)
    }
    protected inline fun <reified T : Event> watch(noinline processor: (T) -> Any?) {
       watch(LambdaProcessor<T>(processor))
    }
}