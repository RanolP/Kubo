package io.github.ranolp.kubo.general.command

import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.side.Side

abstract class CommandParser(val side: Side) {
    class LambdaCommandParser(side: Side, val expression: CommandParser.(Message) -> Result) : CommandParser(side) {
        override fun parse(message: Message): Result = expression(this, message)
    }

    class Result private constructor(val ok: Boolean) {
        constructor(ok: Boolean, commandData: CommandData) : this(ok) {
            _commandData = commandData
        }

        private lateinit var _commandData: CommandData
        val commandData get() = _commandData

        companion object {
            val FAIL = Result(false)
        }
    }

    abstract fun parse(message: Message): Result
}