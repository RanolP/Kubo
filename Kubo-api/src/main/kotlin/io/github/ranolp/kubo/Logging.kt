package io.github.ranolp.kubo

import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class LogLevel(val level: Int) {
    OFF(0), INFO(1), ERROR(2), DEBUG(3)
}

var logLevel = LogLevel.ERROR
val formatter = DateTimeFormatter.ofPattern("hh:mm:ss")

fun log(level: LogLevel, message: String) {
    if (level.level <= logLevel.level) {
        println("[${formatter.format(LocalTime.now())}] [${Thread.currentThread().name}/${level.name}]: $message")
    }
}

fun info(message: String) = log(LogLevel.INFO, message)

fun logError(message: String) = log(LogLevel.ERROR, message)

fun logError(message: Throwable) = log(LogLevel.ERROR, message.toString() + "\n\tat " + message.stackTrace.joinToString("\n\tat "))

fun debug(message: String) = log(LogLevel.DEBUG, message)
fun debug(message: Any?) = log(LogLevel.DEBUG, message?.toString() ?: "null")