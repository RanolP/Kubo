package io.github.ranolp.kubo.telegram.bot.objects

import com.github.salomonbrys.kotson.*
import com.google.gson.JsonObject
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.History
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.util.byNullable

class TelegramBotChat(json: JsonObject) : Chat {
    override val side: Side = Telegram.BOT_SIDE
    val id by json.byLong
    val type by json.byString
    override val title by json.byNullableString
    val username by json.byNullableString
    val firstName by json.byNullableString("first_name")
    val lastName by json.byNullableString("last_name")
    val allMembersAreAdmin by json.byBool("all_members_are_administrators", { false })
    // It will replace by ChatPhoto
    val photo by json.byNullableObject
    val description by json.byNullableString
    val inviteLink by json.byNullableString("invite_link")
    val pinnedMessage by json.byNullable("pinned_message", ::TelegramBotMessage)

    override fun sendMessage(message: String) {
        io.github.ranolp.kubo.telegram.bot.functions.sendMessage().apply {
            chatId = id.toString()
            text = message
        }.request()
    }

    override fun history(): History {
        TODO("not implemented")
    }
}