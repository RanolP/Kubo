package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.mapping

open class Property(map: Map<String, Any?>) {
    protected val map: MutableMap<String, Any?> = if(map is MutableMap) map else map.toMutableMap()
    operator fun get(key: String) = map[key]
    operator fun set(key: String, value: Any) {
        map[key] = value
    }

    protected inline fun <reified T> mapping(name: String? = null) = map.mapping<T>(name)
}