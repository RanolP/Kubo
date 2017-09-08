package io.github.ranolp.kubo.telegram.bot.objects

import com.github.salomonbrys.kotson.*
import com.google.gson.JsonObject
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.general.objects.TelegramUser

class TelegramBotUser(json: JsonObject) : TelegramUser {
    override val side: Side = Telegram.BOT_SIDE
    override val id by json.byInt
    override val isBot by json.byBool("is_bot")
    override val firstName by json.byString("first_name")
    override val lastName by json.byNullableString("last_name")
    override val username by json.byNullableString
    override val languageCode by json.byNullableString("language_code")
    override val isSelf: Boolean
        get() = Telegram.getAdapter().myself === this

    override fun toString(): String {
        return (if (isBot) "(bot)" else "") + " " + firstName + " " + (lastName ?: "") + "{" + (if (username != null) "@$username" else "") + (if (languageCode != null) "($languageCode)" else "") + "}"
    }
}