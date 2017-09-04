package io.github.ranolp.kubo.general.command

class SubCommand(val parentExecutor: Executor, val th: Int, internal var filter: (String) -> Boolean, internal var executorFunction: Executor.(String) -> Unit) : CommandStructure {
    internal val executor by lazy {
        parentExecutor.subExecutor(this)
    }
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
        executorFunction(executor, commandData[th])
        cached = true
        if (commandData.args.size > th + 1) {
            var runOtherwise = true
            val arg = commandData.args[th + 1]
            subCommands.keys.filter { it.filter(arg) }.forEach {
                it(subCommands[it]!!, commandData)
                runOtherwise = false
            }
            if (runOtherwise) {
                otherwise.invoke(executor, commandData.args.slice(0..commandData.args.size - 1).joinToString(" "))
            }
        } else {
            otherwise.invoke(executor, null)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as SubCommand

        if (parentExecutor != other.parentExecutor) return false
        if (th != other.th) return false

        return true
    }

    override fun hashCode(): Int {
        var result = parentExecutor.hashCode()
        result = 31 * result + th
        return result
    }
}