package io.github.ranolp.kubo.general.command

interface CommandStructure {
    fun generateId(): Long

    fun match(filter: (String) -> Boolean = { true }, execute: Executor.(String) -> Unit, dynamic: Boolean = true, id: Long = generateId())

    fun otherwise(executor: Executor.(String?) -> Unit, dynamic: Boolean = true)
}