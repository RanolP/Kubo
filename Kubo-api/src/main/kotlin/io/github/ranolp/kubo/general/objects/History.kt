package io.github.ranolp.kubo.general.objects

interface History {
    val chat: Chat
    fun retrieve(count: Int = 100): List<Message>
}