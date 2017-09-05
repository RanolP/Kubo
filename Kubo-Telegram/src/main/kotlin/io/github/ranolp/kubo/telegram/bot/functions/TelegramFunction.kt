package io.github.ranolp.kubo.telegram.bot.functions

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpPost
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.util.parseJson

abstract class TelegramFunction<T>(private val functionName: String) {
    fun request(): T {
        val (request, response, result) = "https://api.telegram.org/bot${Telegram.getAdapter().getToken()}/$functionName".httpPost(generateArguments().toList()).responseString()
        val (data, error) = result
        if (error != null) {
            throw error
        } else {
            return parse(request, response, data!!)
        }
    }

    inline fun <T> work(result: String, worker: JsonElement.() -> T): T? {
        val jsonObject = parseJson(result).asJsonObject
        if (jsonObject["ok"].asBoolean) {
            return worker(jsonObject["result"])
        }
        return null
    }

    inline fun <T> workObject(result: String, worker: JsonObject.() -> T): T? {
        return work(result, { worker(asJsonObject) })
    }


    open internal fun generateArguments(): Map<String, Any?> = emptyMap()

    abstract internal fun parse(request: Request, response: Response, result: String): T
}