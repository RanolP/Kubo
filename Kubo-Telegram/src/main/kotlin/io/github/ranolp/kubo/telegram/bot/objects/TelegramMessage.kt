package io.github.ranolp.kubo.telegram.bot.objects

import io.github.ranolp.kubo.general.Message

class TelegramMessage(map: Map<String, Any?>) : Message(map) {
    val id by mapping<Long>("message_id")
    override val from by mapping<TelegramUser?>()
    val whenSended by mapping<Long>("date")
    override val chat by mapping<TelegramChat>()
    val forwardFrom by mapping<TelegramUser?>("forward_from")
    val forwardFromChat by mapping<TelegramChat?>("forward_from_chat")
    val forwardFromMessageId by mapping<Long?>("forward_from_message_id")
    val fowardSignature by mapping<String?>("forward_signature")
    val forwardDate by mapping<Long?>("forward_date")
    val replyTo by mapping<TelegramMessage?>("reply_to_message")
    val lastEdited by mapping<Long?>("edit_date")
    val authorSignature by mapping<String?>("author_signature")
    override val text by mapping<String?>()
    // it will replace by List<TelegramMessageEntity>?
    val entities by mapping<List<Any>?>()
    // it will replace by Audio?
    val audio by mapping<Any?>()
    // it will replace by Document?
    val document by mapping<Any?>()
    // it will replace by Game?
    val game by mapping<Any?>()
    // it will replace by List<PhotoSize>?
    val photo by mapping<List<Any>?>()
    // it will replace by Sticker?
    val sticker by mapping<Any?>()
    // it will replace by Video?
    val video by mapping<Any?>()
    // it will replace by Voice?
    val voice by mapping<Any?>()

    /*
video_note	VideoNote	Optional. Message is a video note, information about the video message
new_chat_members	Array of User	Optional. New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
caption	String	Optional. Caption for the document, photo or video, 0-200 characters
contact	Contact	Optional. Message is a shared contact, information about the contact
location	Location	Optional. Message is a shared location, information about the location
venue	Venue	Optional. Message is a venue, information about the venue
new_chat_member	User	Optional. A new member was added to the group, information about them (this member may be the bot itself)
left_chat_member	User	Optional. A member was removed from the group, information about them (this member may be the bot itself)
new_chat_title	String	Optional. A chat title was changed to this value
new_chat_photo	Array of PhotoSize	Optional. A chat photo was change to this value
delete_chat_photo	True	Optional. Service message: the chat photo was deleted
group_chat_created	True	Optional. Service message: the group has been created
supergroup_chat_created	True	Optional. Service message: the supergroup has been created. This field can‘t be received in a message coming through updates, because bot can’t be a member of a supergroup when it is created. It can only be found in reply_to_message if someone replies to a very first message in a directly created supergroup.
channel_chat_created	True	Optional. Service message: the channel has been created. This field can‘t be received in a message coming through updates, because bot can’t be a member of a channel when it is created. It can only be found in reply_to_message if someone replies to a very first message in a channel.
migrate_to_chat_id	Integer	Optional. The group has been migrated to a supergroup with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
migrate_from_chat_id	Integer	Optional. The supergroup has been migrated from a group with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
pinned_message	Message	Optional. Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
invoice	Invoice	Optional. Message is an invoice for a payment, information about the invoice. More about payments »
successful_payment	SuccessfulPayment	Optional. Message is a service message about a successful payment, information about the payment. More about payments »
     */

    override fun toString(): String {
        return "TelegramMessage(id=$id, from=$from, when=$whenSended, chat=$chat)"
    }
}