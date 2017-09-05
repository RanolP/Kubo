package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.side.Sided

interface Message : Sided {
    val text: String?
    val from: User?
    val chat: Chat
}