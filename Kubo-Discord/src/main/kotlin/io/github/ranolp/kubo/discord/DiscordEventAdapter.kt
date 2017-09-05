package io.github.ranolp.kubo.discord

import io.github.ranolp.kubo.discord.event.DiscordHearEvent
import io.github.ranolp.kubo.discord.objects.DiscordMessage
import io.github.ranolp.kubo.general.event.Events
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

object DiscordEventAdapter : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        Events.call(DiscordHearEvent(DiscordMessage(event.message)))
    }
}