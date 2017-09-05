package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.Chat
import net.dv8tion.jda.core.entities.MessageChannel

class DiscordChat(val jdaChat: MessageChannel) : Chat {
    override val side = Discord.SIDE
    override fun sendMessage(message: String) {
        jdaChat.sendMessage(message).complete()
    }
}