package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.Option

fun setUp(adapter1: KuboAdapter<out Option>, vararg adapters: KuboAdapter<out Option>) {
    info("Setup adapters...")
    info("Inserted adapter count : ${adapters.size + 1}")
    setUp(adapter1)
    for (adapter in adapters) {
        setUp(adapter)
    }
}

fun <T : KuboAdapter<out Option>> setUp(adapter: T) {
    info("Setup ${adapter::class.simpleName}...")
    Adapters.put(adapter.side, adapter)
    adapter.login()
    info("Login success.")
}

fun start(bot: Bot) {
    bot.start()
}