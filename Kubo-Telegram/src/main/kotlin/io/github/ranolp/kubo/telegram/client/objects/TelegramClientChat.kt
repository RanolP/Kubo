package io.github.ranolp.kubo.telegram.client.objects

import com.github.badoualy.telegram.api.utils.toInputPeer
import com.github.badoualy.telegram.tl.api.TLChat
import io.github.ranolp.kubo.general.objects.History
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.general.side.Side
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.client.newLongId
import io.github.ranolp.kubo.telegram.general.objects.TelegramChat

class TelegramClientChat(val chat: TLChat) : TelegramChat {
    override val side: Side = Telegram.CLIENT_SIDE
    override val title: String?
        get() = chat.title
    override val users: List<User>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun sendMessage(message: String) {
        Telegram.getClientAdapter().client.messagesSendMessage(chat.toInputPeer()!!, message, newLongId())
    }

    override fun history(): History {
        TODO("not implemented")
    }

    override fun leave(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}