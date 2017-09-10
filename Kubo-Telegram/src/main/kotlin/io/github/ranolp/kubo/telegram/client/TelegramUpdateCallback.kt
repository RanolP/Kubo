package io.github.ranolp.kubo.telegram.client

import com.github.badoualy.telegram.api.TelegramClient
import com.github.badoualy.telegram.api.UpdateCallback
import com.github.badoualy.telegram.tl.api.*
import com.github.badoualy.telegram.tl.core.TLVector
import io.github.ranolp.kubo.general.event.Events
import io.github.ranolp.kubo.telegram.client.event.TelegramClientHearEvent
import io.github.ranolp.kubo.telegram.client.objects.TelegramClientMessage

object TelegramUpdateCallback : UpdateCallback {
    override fun onShortChatMessage(client: TelegramClient, message: TLUpdateShortChatMessage) {
    }

    override fun onShortMessage(client: TelegramClient, message: TLUpdateShortMessage) {
    }

    override fun onShortSentMessage(client: TelegramClient, message: TLUpdateShortSentMessage) {
    }

    override fun onUpdateShort(client: TelegramClient, update: TLUpdateShort) {
        update(update.update)
    }

    override fun onUpdateTooLong(client: TelegramClient) {

    }

    override fun onUpdates(client: TelegramClient, updates: TLUpdates) {
        update(updates.updates)
    }

    override fun onUpdatesCombined(client: TelegramClient, updates: TLUpdatesCombined) {
        update(updates.updates)
    }
    private fun update(updates: TLVector<TLAbsUpdate>) {
        for(update in updates) {
            update(update)
        }
    }

    private fun update(update: TLAbsUpdate) {
        if (update is TLUpdateNewChannelMessage) {
            val msg = update.message
            if (msg is TLMessage) {
                Events.call(TelegramClientHearEvent(TelegramClientMessage(msg)))
            }
        }
    }
}