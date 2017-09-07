package io.github.ranolp.kubo.general.objects

interface History {
    val chat: Chat
    val empty: Boolean
    fun retrieve(count: Int = 100): Array<Message>
}