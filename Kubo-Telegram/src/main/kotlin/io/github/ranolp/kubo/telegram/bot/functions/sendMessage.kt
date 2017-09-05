package io.github.ranolp.kubo.telegram.bot.functions

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import io.github.ranolp.kubo.telegram.bot.objects.ReplyMarkup
import io.github.ranolp.kubo.telegram.bot.objects.TelegramMessage
import io.github.ranolp.kubo.telegram.errors.NotOkError
import io.github.ranolp.kubo.telegram.general.MessageParseMode
import io.github.ranolp.kubo.telegram.util.notNull

class sendMessage : TelegramFunction<TelegramMessage>("sendMessage") {
    companion object {
        operator fun invoke(chatId: String, text: String, parseMode: MessageParseMode? = null, webPreview: Boolean? = null, notify: Boolean? = null, replyTo: Int? = null, replyMarkup: ReplyMarkup? = null): TelegramMessage {
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

    override fun parse(request: Request, response: Response, result: String): TelegramMessage {
        return work(result, ::TelegramMessage) ?: throw NotOkError
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