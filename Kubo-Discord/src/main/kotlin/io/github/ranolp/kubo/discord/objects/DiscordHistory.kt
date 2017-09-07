package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.History
import io.github.ranolp.kubo.general.objects.Message
import net.dv8tion.jda.core.entities.MessageHistory

class DiscordHistory(val jdaHistory: MessageHistory) : History {
    override val chat: Chat by lazy {
        DiscordChat(jdaHistory.channel)
    }

    override fun retrieve(count: Int): List<Message> {
        return jdaHistory.retrievePast(count).complete().map(::DiscordMessage)
    }
}