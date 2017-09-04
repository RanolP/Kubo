package io.github.ranolp.kubo.general

import kotlin.properties.Delegates

abstract class Message(map: Map<String, Any?>) : Property(map) {
    open val text: String? = null
    open val from: User? = null
    open val chat: Chat by Delegates.notNull()
}