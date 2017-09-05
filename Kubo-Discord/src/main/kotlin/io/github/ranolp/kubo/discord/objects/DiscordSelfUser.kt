package io.github.ranolp.kubo.discord.objects

import net.dv8tion.jda.core.entities.SelfUser

class DiscordSelfUser internal constructor(val selfUser: SelfUser) : DiscordUser(selfUser) {}