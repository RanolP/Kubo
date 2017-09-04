package io.github.ranolp.kubo.general

abstract class Chat(map: Map<String, Any?>) : Property(map) {
    abstract fun sendMessage(message: String)
}