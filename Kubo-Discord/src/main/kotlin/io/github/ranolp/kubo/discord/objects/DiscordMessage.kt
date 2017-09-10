package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class DiscordMessage(val jdaMessage: net.dv8tion.jda.core.entities.Message) : Message {
    override val side: Side = Discord.SIDE
    override val text: String? = jdaMessage.rawContent
    override val from: User? by lazy {
        DiscordUser(jdaMessage.author)
    }
    override val chat: Chat by lazy {
        DiscordChat(jdaMessage.channel)
    }
    override val whenSended: LocalDateTime by lazy {
        jdaMessage.creationTime.toLocalDateTime().plus(TimeZone.getDefault().rawOffset.toLong(), ChronoUnit.MILLIS)
    }

    override fun delete() {
        jdaMessage.delete().complete()
    }

    override fun edit(message: String) {
        jdaMessage.editMessage(message).complete()
    }
}