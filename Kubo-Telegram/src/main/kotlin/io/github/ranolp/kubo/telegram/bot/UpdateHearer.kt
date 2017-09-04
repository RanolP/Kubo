package io.github.ranolp.kubo.telegram.bot

import io.github.ranolp.kubo.telegram.bot.objects.Update

internal abstract class UpdateHearer {
    lateinit var receive: (Update) -> Unit

    abstract fun start()
}