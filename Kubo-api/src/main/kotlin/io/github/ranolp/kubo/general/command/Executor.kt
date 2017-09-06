package io.github.ranolp.kubo.general.command

import io.github.ranolp.kubo.general.Chat
import io.github.ranolp.kubo.general.Message
import io.github.ranolp.kubo.general.User
import io.github.ranolp.kubo.general.side.Side

open class Executor internal constructor(private val command: CommandStructure) {
    private class SubExecutor(private val parent: Executor, command: CommandStructure) : Executor(command) {
        override val commandData: CommandData
            get() = parent.commandData
    }

    internal var _commandData: CommandData? = null
    open val commandData: CommandData
        get() = _commandData ?: throw KotlinNullPointerException("command data")
    val message: Message
        get() = commandData.message
    val from: User?
        get() = message.from
    val chat: Chat?
        get() = message.chat
    val side: Side
        get() = message.side

    internal fun subExecutor(command: CommandStructure): Executor = SubExecutor(this, command)

    fun reply(text: String) {
        commandData.reply(text)
    }

    fun send(text: String) {
        commandData.send(text)
    }

    fun dynamic(max: Int, block: Executor.() -> Unit) {
        val last = when (command) {
            is Command -> command.lastId
            is SubCommand -> command.lastId
            else -> throw IllegalStateException("CommandStructure must be command or sub-command")
        }
        block()
        when (command) {
            is Command -> command.lastId = last + max + 1
            is SubCommand -> command.lastId = last + max + 1
        }
    }

    fun match(filter: (String) -> Boolean = { true }, dynamic: Boolean = true, executor: Executor.(String) -> Unit) {
        command.match(filter, executor, dynamic)
    }

    fun match(filter: String, dynamic: Boolean = true, executor: Executor.(String) -> Unit) {
        command.match({ filter.equals(it, true) }, executor, dynamic)
    }

    inline fun <reified T> filterMatch(crossinline parser: (String) -> T, noinline matcher: (String) -> Boolean = { true }, dynamic: Boolean = true, crossinline executor: Executor.(T) -> Unit) {
        match(matcher, dynamic) {
            executor(parser(it))
        }
    }

    fun otherwise(executor: Executor.(String?) -> Unit) {
        command.otherwise(executor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Executor) return false

        if (command != other.command) return false

        return true
    }

    override fun hashCode(): Int {
        return command.hashCode()
    }
}