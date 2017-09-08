package io.github.ranolp.kubo.telegram.general.objects

import io.github.ranolp.kubo.general.objects.User

interface TelegramUser : User {
    val firstName: String
    val lastName: String?
    val username: String?
    val languageCode: String?
    val isBot: Boolean
    val id: Int
    override val displayName: String
        get() = "$firstName ${lastName ?: ""}".trim()
}