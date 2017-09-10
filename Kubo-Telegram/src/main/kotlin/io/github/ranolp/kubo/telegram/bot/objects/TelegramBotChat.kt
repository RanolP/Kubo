package io.github.ranolp.kubo.telegram.bot.objects

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.salomonbrys.kotson.*
import com.google.gson.JsonObject
import io.github.ranolp.kubo.general.error.CannotDeleteError
import io.github.ranolp.kubo.general.objects.History
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.bot.functions.TelegramFunction
import io.github.ranolp.kubo.telegram.errors.NotOkError
import io.github.ranolp.kubo.telegram.general.MessageParseMode
import io.github.ranolp.kubo.telegram.general.objects.TelegramChat
import io.github.ranolp.kubo.telegram.util.byNullable
import io.github.ranolp.kubo.telegram.util.notNull

class TelegramBotChat(json: JsonObject) : TelegramChat {
    internal class sendMessage : TelegramFunction<TelegramBotMessage>("sendMessage") {
        companion object {
            operator fun invoke(chatId: String, text: String, parseMode: MessageParseMode? = null,
                    webPreview: Boolean? = null, notify: Boolean? = null, replyTo: Int? = null,
                    replyMarkup: ReplyMarkup? = null): TelegramBotMessage {
                return sendMessage().apply {
                    this.chatId = chatId
                    this.text = text
                    this.parseMode = parseMode
                    this.webPreview = webPreview
                    this.notify = notify
                    this.replyTo = replyTo
                    this.replyMarkup = replyMarkup
                }.request()
            }
        }

        lateinit var chatId: String
        lateinit var text: String
        var parseMode: MessageParseMode? = null
        var webPreview: Boolean? = null
        // only available on channel
        var notify: Boolean? = null
        // reply message id
        var replyTo: Int? = null
        var replyMarkup: ReplyMarkup? = null

        override fun parse(request: Request, response: Response, result: String): TelegramBotMessage {
            return workObject(result, ::TelegramBotMessage) ?: throw NotOkError
        }

        override fun generateArguments(): Map<String, Any?> {
            return hashMapOf("chat_id" to chatId, "text" to text).apply {
                parseMode.notNull { put("parse_mode", toString()) }
                webPreview.notNull { put("disable_web_page_preview", not().toString()) }
                notify.notNull { put("disable_notification", not().toString()) }
                replyTo.notNull { put("reply_to_message_id", toString()) }
                replyMarkup.notNull { put("reply_markup", toString()) }
            }
        }
    }

    private object leaveChat : TelegramFunction<TelegramBotChat>("leaveChat") {
        private lateinit var chatId: String
        operator fun invoke(chat: TelegramBotChat): TelegramBotChat {
            chatId = chat.id.toString()
            return request()
        }

        override fun parse(request: Request, response: Response, result: String): TelegramBotChat {
            return workObject(result, ::TelegramBotChat)!!
        }

        override fun generateArguments(): Map<String, Any?> {
            return mapOf("chat_id" to chatId)
        }
    }

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
    override val users: List<User> = emptyList() // Can't get ;(

    override fun sendMessage(message: String) {
        sendMessage().apply {
            chatId = id.toString()
            text = message
        }.request()
    }

    override fun history(): History {
        TODO("not implemented")
    }


    override fun leave(): Boolean {
        return try {
            leaveChat(this)
            true
        } catch(e: Throwable) {
            false
        }
    }

    override fun delete() {
        throw CannotDeleteError
    }
}