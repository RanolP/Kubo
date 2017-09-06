package io.github.ranolp.kubo.general.command

import io.github.ranolp.kubo.general.objects.Message

class CommandData(val label: String, val message: Message, val args: List<String>) {
    val caller = message.from

    operator fun get(index: Int) = args[index]

    fun reply(text: String) {
        message.chat.sendMessage(text)
    }

    fun send(text: String) {
        message.chat.sendMessage(text)
    }
}