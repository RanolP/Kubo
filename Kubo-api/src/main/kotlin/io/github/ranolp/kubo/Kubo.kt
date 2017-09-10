package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.Option
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side

fun setUp(adapter1: KuboAdapter<Option>, vararg adapters: KuboAdapter<Option>, ignoreError: Boolean = true) {
    info("Setup adapters...")
    info("Inserted adapter count : ${adapters.size + 1}")
    setUp(adapter1)
    for (adapter in adapters) {
        setUp(adapter, ignoreError)
    }
}

fun <T : KuboAdapter<Option>> setUp(adapter: T, ignoreError: Boolean = true) {
    info("Setup ${adapter::class.simpleName}...")
    Adapters.put(adapter.side, adapter)
    try {
        adapter.login()
        info("Login success.")
    } catch(e: Throwable) {
        logError(e)
        if (!ignoreError) {
            System.exit(0)
        }
    }
}

fun start(bot: Bot) {
    bot.start()
}

fun myselfOf(side: Side): User? = Adapters.getAdapter(side)?.myself