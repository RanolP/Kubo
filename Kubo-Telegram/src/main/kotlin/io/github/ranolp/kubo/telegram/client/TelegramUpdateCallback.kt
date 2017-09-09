package io.github.ranolp.kubo.telegram.client

import com.github.badoualy.telegram.api.TelegramClient
import com.github.badoualy.telegram.api.UpdateCallback
import com.github.badoualy.telegram.tl.api.*

object TelegramUpdateCallback : UpdateCallback {
    override fun onShortChatMessage(client: TelegramClient, message: TLUpdateShortChatMessage) {
        client.messagesGetFullChat(message.chatId)
    }

    override fun onShortMessage(client: TelegramClient, message: TLUpdateShortMessage) {

    }

    override fun onShortSentMessage(client: TelegramClient, message: TLUpdateShortSentMessage) {

    }

    override fun onUpdateShort(client: TelegramClient, update: TLUpdateShort) {

    }

    override fun onUpdateTooLong(client: TelegramClient) {

    }

    override fun onUpdates(client: TelegramClient, updates: TLUpdates) {
    }

    override fun onUpdatesCombined(client: TelegramClient, updates: TLUpdatesCombined) {

    }
}