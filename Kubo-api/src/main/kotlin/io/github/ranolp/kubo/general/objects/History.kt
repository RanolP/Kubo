package io.github.ranolp.kubo.general.objects

interface History {
    val chat: Chat
    val empty: Boolean
    fun retrieve(): Array<Message>
}