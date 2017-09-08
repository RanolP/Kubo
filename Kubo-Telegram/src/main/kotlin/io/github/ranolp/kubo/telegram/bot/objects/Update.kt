package io.github.ranolp.kubo.telegram.bot.objects

import com.github.salomonbrys.kotson.byLong
import com.google.gson.JsonObject
import io.github.ranolp.kubo.telegram.util.byNullable

class Update(json: JsonObject) {
    enum class Type {
        MESSAGE, EDIT_CHANNEL_POST, CALLBACK_QUERY
    }

    val id by json.byLong("update_id")
    val message by json.byNullable("message", ::TelegramBotMessage)
}