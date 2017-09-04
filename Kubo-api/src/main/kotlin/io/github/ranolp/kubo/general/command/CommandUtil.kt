package io.github.ranolp.kubo.general.command

fun command(name: String, description: String? = null, vararg aliases: String = emptyArray(), executorFunction: Executor.(CommandData) -> Unit) {
    val command = Command(name, description, *aliases, executorFunction = executorFunction)
    Commands.register(command)
}