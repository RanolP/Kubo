package io.github.ranolp.kubo.general.side

import io.github.ranolp.kubo.general.Processor
import io.github.ranolp.kubo.general.event.Event
import java.util.ArrayList
import kotlin.collections.HashMap

class ProcessorCombinator<T : Event> : Processor<T>() {
    private val processors = HashMap<Side, MutableList<Processor<in T>>>()

    operator fun plusAssign(pair: Pair<Side, Processor<in T>>) {
        processors.getOrPut(pair.first, { ArrayList<Processor<in T>>() }).add(pair.second)
    }

    override fun execute(event: T) {
        processors[event.side]?.forEach { it(event) }
        processors[Side.ANY]?.forEach { it(event) }
    }
}