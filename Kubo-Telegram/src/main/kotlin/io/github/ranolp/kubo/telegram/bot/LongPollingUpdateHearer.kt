package io.github.ranolp.kubo.telegram.bot

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import io.github.ranolp.kubo.debug
import io.github.ranolp.kubo.logError
import io.github.ranolp.kubo.telegram.bot.functions.TelegramFunction
import io.github.ranolp.kubo.telegram.bot.objects.Update
import io.github.ranolp.kubo.telegram.errors.NotOkError
import io.github.ranolp.kubo.telegram.util.notNull
import io.github.ranolp.kubo.telegram.util.toPrimitiveMap
import kotlin.concurrent.thread

internal object LongPollingUpdateHearer : UpdateHearer() {
    private object getUpdates : TelegramFunction<List<Update>>("getUpdates") {
        var offset: Long? = null
        var timeout: Int? = null
        // not supported
        var allowedUpdateTypes: List<Update.Type>? = null

        operator fun invoke() = request()
        override fun parse(request: Request, response: Response, result: String): List<Update> {
            return work(result) {
                asJsonArray.map { Update(it.asJsonObject.toPrimitiveMap()) }
            } ?: throw NotOkError
        }

        override fun generateArguments(): Map<String, Any?> {
            return hashMapOf<String, Any?>().apply {
                offset.notNull { put("offset", offset) }
                timeout.notNull { put("timeout", timeout) }
                allowedUpdateTypes.notNull { TODO("allowed update types is not implemented") }
            }
        }
    }

    var daemon: Boolean = false
    var timeout: Int?
        get() = getUpdates.timeout
        set(value) {
            getUpdates.timeout = value
        }
    var updateTypes: List<Update.Type>?
        get() = getUpdates.allowedUpdateTypes
        set(value) {
            getUpdates.allowedUpdateTypes = if (value?.isEmpty() ?: true) null else value
        }

    var thread: Thread? = null
    override fun start() {
        if (thread == null) {
            this.thread = thread(name = "TelegramBot-LongPollingReceiver", isDaemon = daemon) {
                var offset: Long = 0
                while (true) {
                    getUpdates.offset = offset + 1
                    try {
                        val updates = getUpdates()
                        if (!updates.isEmpty()) {
                            debug("${updates.size} updates received")
                            for (update in updates) {
                                receive(update)
                                offset = maxOf(offset, update.id)
                            }
                        }
                    } catch(e: Throwable) {
                        logError(e)
                    }
                }
            }
        }
    }
}