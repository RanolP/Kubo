package io.github.ranolp.kubo.telegram.client.objects

import com.github.badoualy.telegram.tl.api.TLMessage
import io.github.ranolp.kubo.general.objects.Chat
import io.github.ranolp.kubo.general.objects.Message
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import java.time.LocalDateTime

class TelegramClientMessage(val message: TLMessage) : Message {
    override val side: Side = Telegram.CLIENT_SIDE
    override val text: String?
        get() = message.message
    override val from: User?
        get() = TODO("not implemented")
    override val chat: Chat
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val whenSended: LocalDateTime
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edit(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}