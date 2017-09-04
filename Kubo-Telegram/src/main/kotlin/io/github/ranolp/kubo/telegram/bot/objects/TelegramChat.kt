package io.github.ranolp.kubo.telegram.bot.objects

import io.github.ranolp.kubo.general.Chat

class TelegramChat(map: Map<String, Any?>) : Chat(map) {
    val id by mapping<Long>()
    val type by mapping<String>()
    val title by mapping<String?>()
    val username by mapping<String?>()
    val firstName by mapping<String?>("first_name")
    val lastName by mapping<String?>("last_name")
    val allMembersAreAdmin by mapping<Boolean?>("all_members_are_administrators")
    // It will replace by ChatPhoto
    val photo by mapping<Any?>()
    val description by mapping<String?>()
    val inviteLink by mapping<String?>("invite_link")
    val pinnedMessage by mapping<TelegramMessage?>("pinned_message")

    override fun sendMessage(message: String) {
        io.github.ranolp.kubo.telegram.bot.functions.sendMessage().apply {
            chatId = id.toString()
            text = message
        }.request()
    }
}