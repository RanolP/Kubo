package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side

class DiscordMessage(val jdaMessage: net.dv8tion.jda.core.entities.Message) : Message {
    override val side: Side = Discord.SIDE
    override val text = jdaMessage.rawContent
    override val from: User? by lazy {
        DiscordUser(jdaMessage.author)
    }
    override val chat: Chat by lazy {
        DiscordChat(jdaMessage.channel)
    }

    override fun delete() {
        jdaMessage.delete().complete()
    }
}