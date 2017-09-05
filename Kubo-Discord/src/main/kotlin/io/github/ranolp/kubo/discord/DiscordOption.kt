package io.github.ranolp.kubo.discord

import io.github.ranolp.kubo.general.Option

class DiscordOption(val token: String, val bot: Boolean = true, val commandPrefix: Char = '!') : Option(Discord.SIDE) {}