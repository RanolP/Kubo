package io.github.ranolp.kubo.discord.objects

import io.github.ranolp.kubo.discord.Discord
import io.github.ranolp.kubo.general.error.CannotDeleteError
import io.github.ranolp.kubo.general.error.NotOwnerError
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.History
import io.github.ranolp.kubo.general.objects.User
import net.dv8tion.jda.client.entities.Group
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.PrivateChannel
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.exceptions.PermissionException

class DiscordChat(val jdaChat: MessageChannel) : Chat {
    override val side = Discord.SIDE
    override val title: String?
        get() = jdaChat.name
    override val users: List<User> by lazy {
        when (jdaChat) {
            is Group -> jdaChat.users
            is PrivateChannel -> listOf(jdaChat.user)
            is TextChannel -> jdaChat.members.map { it.user }
            else -> emptyList()
        }.map { DiscordUser(it) }
    }

    override fun sendMessage(message: String) {
        jdaChat.sendMessage(message).complete()
    }

    override fun history(): History {
        return DiscordHistory(jdaChat.history)
    }


    override fun leave(): Boolean {
        return when (jdaChat) {
            is Group -> {
                jdaChat.leaveGroup().complete()
                true
            }
            is TextChannel -> {
                jdaChat.guild.leave().complete()
                true
            }
            else -> false
        }
    }

    override fun delete() {
        try {
            when (jdaChat) {
                is TextChannel -> jdaChat.guild.delete().complete()
                else -> throw CannotDeleteError

            }
        } catch(e: PermissionException) {
            throw NotOwnerError
        }
    }
}