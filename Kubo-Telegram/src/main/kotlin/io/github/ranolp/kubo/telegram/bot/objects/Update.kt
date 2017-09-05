package io.github.ranolp.kubo.telegram.bot.objects

class Update(map: Map<String, Any?>) : Property(map) {
    enum class Type {
        MESSAGE, EDIT_CHANNEL_POST, CALLBACK_QUERY
    }

    val id by mapping<Long>("update_id")
    val message by mapping<TelegramMessage?>()
}