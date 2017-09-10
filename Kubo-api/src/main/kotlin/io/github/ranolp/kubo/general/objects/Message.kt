package io.github.ranolp.kubo.general.objects

import io.github.ranolp.kubo.general.error.CannotDeleteError
import io.github.ranolp.kubo.general.error.NotOwnerError
import io.github.ranolp.kubo.general.side.Sided

interface Message : Sided {
    val text: String?
    val from: User?
    val chat: Chat
    val own: Boolean
        get() {
            val tmp = from
            return tmp != null && tmp.isSelf
        }

    @Throws(NotOwnerError::class, CannotDeleteError::class) fun delete()
    @Throws(NotOwnerError::class) fun edit(message: String)

}