package io.github.ranolp.kubo.general.objects

import io.github.ranolp.kubo.general.error.CannotDeleteError
import io.github.ranolp.kubo.general.error.NotOwnerError
import io.github.ranolp.kubo.general.side.Sided

interface Chat : Sided {
    val title: String?
    val users: List<User>

    fun sendMessage(message: String)
    fun history(): History
    fun leave(): Boolean
    @Throws(NotOwnerError::class, CannotDeleteError::class) fun delete()
}