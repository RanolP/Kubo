package io.github.ranolp.kubo.general.command

import io.github.ranolp.kubo.debug


class Command(val name: String, val description: String? = null, vararg val aliases: String = emptyArray(), val executorFunction: Executor.(CommandData) -> Unit) : CommandStructure {
    private val idMap = hashMapOf<Long, SubCommand>()
    private val subCommands = hashMapOf<SubCommand, Executor>()
    private var otherwise: Executor.(String?) -> Unit = {}
    private var cached = false
    internal var lastId = 0L

    override fun generateId(): Long {
        return lastId++
    }

    override fun match(filter: (String) -> Boolean, execute: Executor.(String) -> Unit, dynamic: Boolean, id: Long) {
        val subCommand = idMap[id]
        if (!cached || (dynamic && subCommand == null)) {
            val executor = Commands.executor(this)!!
            val command = SubCommand(executor, 0, filter, execute)
            subCommands += command to executor.subExecutor(command)
            idMap[id] = command
        } else if (dynamic && subCommand != null) {
            subCommand.filter = filter
            subCommand.executorFunction = execute
        }
    }

    override fun otherwise(executor: Executor.(String?) -> Unit, dynamic: Boolean) {
        if (dynamic || !cached) {
            otherwise = executor
        }
    }

    operator fun invoke(executor: Executor, commandData: CommandData) {
        lastId = 0L
        executor._commandData = commandData
        executorFunction(executor, commandData)
        cached = true
        if (commandData.args.isNotEmpty()) {
            var runOtherwise = true
            val arg = commandData.args[0]
            subCommands.keys.filter { it.filter(arg) }.forEach {
                it(subCommands[it]!!, commandData)
                runOtherwise = false
            }
            if (runOtherwise) {
                otherwise(executor, commandData.args.joinToString(" "))
            }
        } else {
            otherwise(executor, null)
        }
        executor._commandData = null
    }
}