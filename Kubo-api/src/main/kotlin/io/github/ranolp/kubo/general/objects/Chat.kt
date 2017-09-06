package io.github.ranolp.kubo.general.objects

import io.github.ranolp.kubo.general.side.Sided

interface Chat : Sided {
    val title: String?
    fun sendMessage(message: String)
}