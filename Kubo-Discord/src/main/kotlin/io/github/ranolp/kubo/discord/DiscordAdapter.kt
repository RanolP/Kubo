package io.github.ranolp.kubo.discord

import io.github.ranolp.kubo.KuboAdapter
import io.github.ranolp.kubo.general.command.CommandData
import io.github.ranolp.kubo.general.command.CommandParser
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder

class DiscordAdapter(option: DiscordOption) : KuboAdapter<DiscordOption>(option, Discord.SIDE) {
    private lateinit var jda: JDA
    override fun login() {
        jda = JDABuilder(if (option.bot) AccountType.BOT else AccountType.CLIENT).apply {
            setToken(option.token)
        }.buildBlocking()
        jda.addEventListener(DiscordEventAdapter)

        commandParser {
            val raw = it.text!!
            if (raw[0] != option.commandPrefix) {
                CommandParser.Result.FAIL
            } else {
                val space = raw.indexOf(' ')
                val name = when {
                    space != -1 -> raw.substring(1..space - 1)
                    else -> raw.substring(1)
                }
                val arguments = when (space) {
                    -1 -> emptyList<String>()
                    else -> raw.substring(space + 1).split(' ')
                }
                CommandParser.Result(true, CommandData(name, it, arguments))
            }
        }
    }

    override fun logout() {
    }
}