package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.LambdaProcessor
import io.github.ranolp.kubo.general.Option
import io.github.ranolp.kubo.general.Processor
import io.github.ranolp.kubo.general.command.CommandParser
import io.github.ranolp.kubo.general.command.Commands
import io.github.ranolp.kubo.general.event.Event
import io.github.ranolp.kubo.general.event.Events
import io.github.ranolp.kubo.general.event.HearEvent
import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.general.side.Sided
import kotlin.reflect.KClass

abstract class KuboAdapter<out T : Option>(protected val option: T, override val side: Side) : Sided {
    abstract val myself: User
    abstract fun login()

    abstract fun logout()

    private var commandParser: CommandParser? = null

    init {
        watch<HearEvent> {
            val tmp = commandParser
            if (tmp != null && it.side == tmp.side && it.message.text != null && !it.message.own) {
                val result = tmp.parse(it.message)
                if (result.ok) {
                    Commands.dispatch(result.commandData)
                }
            }
        }
    }

    fun isSupported(a: KClass<in Event>) {

    }

    protected inline fun <reified T : Event> watch(processor: Processor<T>) {
        Events.watch(T::class, processor)
    }

    protected inline fun <reified T : Event> watch(noinline processor: (T) -> Any?) {
        watch(LambdaProcessor<T>(processor))
    }

    fun fireEvent(event: Event) {
        Events.call(event)
    }

    protected fun commandParser(commandParser: CommandParser) {
        this.commandParser = commandParser
    }

    protected fun commandParser(commandParser: CommandParser.(Message) -> CommandParser.Result) {
        this.commandParser = CommandParser.LambdaCommandParser(side, commandParser)
    }
}