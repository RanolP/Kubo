package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.History
import net.dv8tion.jda.core.entities.MessageChannel

class DiscordChat(val jdaChat: MessageChannel) : Chat {
    override val side = Discord.SIDE
    override val title: String?
        get() = jdaChat.name
    override fun sendMessage(message: String) {
        jdaChat.sendMessage(message).complete()
    }

    override fun history(): History {
        return DiscordHistory(jdaChat.history)
    }
}