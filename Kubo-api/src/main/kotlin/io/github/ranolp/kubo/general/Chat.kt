package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.side.Sided

interface Chat : Sided {
    fun sendMessage(message: String)
}