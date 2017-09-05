package io.github.ranolp.kubo.telegram.bot.objects

import com.github.salomonbrys.kotson.byBool
import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byNullableString
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonObject
import io.github.ranolp.kubo.general.User
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram

class TelegramUser(json: JsonObject) : User {
    override val side: Side = Telegram.BOT_SIDE
    val id by json.byLong
    val isBot by json.byBool("is_bot")
    val firstName by json.byString("first_name")
    val lastName by json.byNullableString("last_name")
    val username by json.byNullableString
    val languageCode by json.byNullableString("language_code")
    override val displayName: String by lazy {
        "$firstName ${lastName ?: ""}".trim()
    }

    override fun toString(): String {
        return (if (isBot) "(bot)" else "") + " " + firstName + " " + (lastName ?: "") + "{" + (if (username != null) "@$username" else "") + (if (languageCode != null) "($languageCode)" else "") + "}"
    }
}