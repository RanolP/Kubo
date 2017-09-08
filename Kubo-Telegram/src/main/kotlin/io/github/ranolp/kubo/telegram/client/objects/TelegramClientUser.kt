package io.github.ranolp.kubo.telegram.client.objects

import com.github.badoualy.telegram.tl.api.TLUser
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.general.objects.TelegramUser

class TelegramClientUser(val user: TLUser) : TelegramUser {
    override val side: Side = Telegram.CLIENT_SIDE
    override val firstName: String
        get() = user.firstName
    override val lastName: String?
        get() = user.lastName
    override val username: String?
        get() = user.username
    override val isSelf: Boolean
        get() = user.self
    override val languageCode: String
        get() = user.langCode
    override val isBot: Boolean
        get() = user.bot
    override val id: Int
        get() = user.id

    val contact: Boolean
        get() = user.contact
    val mutualContact: Boolean
        get() = user.mutualContact
    val deleted: Boolean
        get() = user.deleted
    val botChatHistory: Boolean
        get() = user.botChatHistory
    val botNochats: Boolean
        get() = user.botNochats
    val verified: Boolean
        get() = user.verified
    val restricted: Boolean
        get() = user.restricted
    val min: Boolean
        get() = user.min
    val botInlineGeo: Boolean
        get() = user.botInlineGeo
    val accessHash: Long
        get() = user.accessHash
    val phone: String
        get() = user.phone
    // photo
    // status
    val botInfoVersion: Int
        get() = user.botInfoVersion
    val restrictionReason: String
        get() = user.restrictionReason
    val botInlinePlaceholder: String
        get() = user.botInlinePlaceholder
}