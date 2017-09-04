package io.github.ranolp.kubo.general.command

object Commands {
    val onRegistered = hashSetOf<() -> Unit>()
    private var _registered = false
    val registered get() = _registered
    internal val _registeredCommands = hashMapOf<String, Command>()
    internal val executors = hashMapOf<Command, Executor>()
    val registeredCommands get() = _registeredCommands.toMap()

    private fun check() {
        if (!_registered) {
            _registered = true
            onRegistered.forEach { it() }
        }
    }

    internal fun executor(command: Command) = executors[command]

    fun register(command: Command) {
        check()
        _registeredCommands[command.name.toLowerCase()] = command
        command.aliases.forEach { _registeredCommands[it.toLowerCase()] = command }
        executors[command] = Executor(command)
    }

    fun dispatch(commandData: CommandData) {
        registeredCommands[commandData.label.toLowerCase()]?.let { it(executors[it]!!, commandData) }
    }
}