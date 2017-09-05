package io.github.ranolp.kubo.discord.event

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.discord.objects.DiscordMessage
import io.github.ranolp.kubo.general.event.HearEvent

class DiscordHearEvent(message: DiscordMessage) : HearEvent(Discord.SIDE, message) {}