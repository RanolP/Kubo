package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.User

open class DiscordUser internal constructor(val jdaUser: net.dv8tion.jda.core.entities.User) : User {
    override val side = Discord.SIDE
    override val displayName: String
        get() = jdaUser.name

    override fun toString(): String {
        return displayName
    }
}