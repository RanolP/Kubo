package io.github.ranolp.kubo.general.event

import io.github.ranolp.kubo.general.LambdaProcessor
import io.github.ranolp.kubo.general.Processor
import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses

object Events {
    internal val map = hashMapOf<KClass<*>, MutableSet<Processor<*>>>()

    fun <T : Event> call(event: T) {
        val clazz = event::class
        clazz.superclasses.map(map::get).filterNotNull().flatten().forEach({ (it as Processor<T>)(event) })
        map[clazz]?.forEach { (it as Processor<T>)(event) }
    }

    fun <T : Event> watch(clazz: KClass<T>, processor: Processor<T>) {
        map.getOrPut(clazz, { hashSetOf() }) += processor
    }

    inline fun <reified T : Event> watch(processor: Processor<T>) {
        watch(T::class, processor)
    }

    inline fun <reified T : Event> watch(noinline processor: (T) -> Any?) {
        watch<T>(LambdaProcessor<T>(processor))
    }
}