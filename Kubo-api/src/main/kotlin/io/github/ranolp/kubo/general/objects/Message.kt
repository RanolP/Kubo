package io.github.ranolp.kubo.general.objects

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

    fun delete()
}