package io.github.ranolp.kubo.telegram.bot.objects

import io.github.ranolp.kubo.general.User

class TelegramUser(map: Map<String, Any?>) : User(map) {
    val id by mapping<Long>()
    val isBot by mapping<Boolean>("is_bot")
    val firstName by mapping<String>("first_name")
    val lastName by mapping<String?>("last_name")
    val username by mapping<String?>()
    val languageCode by mapping<String?>("language_code")
    override val displayName: String by lazy {
        "$firstName ${lastName ?: ""}".trim()
    }

    override fun toString(): String {
        return (if (isBot) "(bot)" else "") + " " + firstName + " " + (lastName ?: "") + "{" + (if (username != null) "@$username" else "") + (if (languageCode != null) "($languageCode)" else "") + "}"
    }
}