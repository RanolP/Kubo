package io.github.ranolp.kubo.telegram.util

inline fun <reified T, R> T?.notNull(callback: T.() -> R) {
    this?.let(callback)
}