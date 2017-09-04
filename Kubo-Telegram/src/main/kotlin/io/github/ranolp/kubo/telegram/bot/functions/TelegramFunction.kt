package io.github.ranolp.kubo.telegram.bot.functions

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpPost
import com.google.gson.JsonElement
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.util.parseJson
import io.github.ranolp.kubo.telegram.util.toPrimitiveMap

abstract class TelegramFunction<T>(private val functionName: String) {
    fun request(): T {
        val (request, response, result) = "https://api.telegram.org/bot${Telegram.getAdapter()!!.getToken()}/$functionName".httpPost(generateArguments().toList()).responseString()
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
    inline fun <T> workMap(result: String, worker: (Map<String, Any?>) -> T): T? {
        val jsonObject = parseJson(result).asJsonObject
        if (jsonObject["ok"].asBoolean) {
            return worker(jsonObject["result"].asJsonObject.toPrimitiveMap())
        }
        return null
    }


    open internal fun generateArguments(): Map<String, Any?> = emptyMap()

    abstract internal fun parse(request: Request, response: Response, result: String): T
}