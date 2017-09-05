package io.github.ranolp.kubo.discord

import io.github.ranolp.kubo.KuboAdapter
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder

class DiscordAdapter(option: DiscordOption) : KuboAdapter<DiscordOption>(option, Discord.SIDE) {
    private lateinit var jda: JDA
    override fun login() {
        jda = JDABuilder(if(option.bot) AccountType.BOT else AccountType.CLIENT).apply {
           setToken(option.token)
        }.buildBlocking()
        jda.addEventListener()

    }

    override fun logout() {

    }
}