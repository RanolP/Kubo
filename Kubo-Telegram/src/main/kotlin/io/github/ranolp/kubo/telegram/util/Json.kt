package io.github.ranolp.kubo.telegram.util

import com.github.salomonbrys.kotson.getNotNull
import com.github.salomonbrys.kotson.toJson
import com.github.salomonbrys.kotson.toMap
import com.google.gson.*

val JSON_PARSER = JsonParser()

fun parseJson(str: String) = JSON_PARSER.parse(str)!!

fun JsonElement.toPrimitive(): Any? = when (this) {
    is JsonObject -> toPrimitiveMap()
    is JsonArray -> toPrimitiveList()
    is JsonPrimitive -> {
        if (isBoolean) asBoolean
        else if (isNumber) asNumber.toJson().asDouble
        else if (isString) asString
        else null
    }
    else -> null
}

fun JsonObject.toPrimitiveMap(): Map<String, Any?> {
    val result = hashMapOf<String, Any?>()
    toMap().forEach { result[it.key] = it.value.toPrimitive() }
    return result
}

fun JsonArray.toPrimitiveList() = map { it.toPrimitive() }

fun <T> JsonObject.byNullable(name: String, constructor: (JsonObject) -> T): Lazy<T?> = lazy {
    val got = this[name]
    if (got != null && got.isJsonObject) {
        constructor(got.asJsonObject)
    } else {
        null
    }
}

fun <T> JsonObject.by(name: String, constructor: (JsonObject) -> T): Lazy<T> = lazy {
    constructor(this.getNotNull(name) as JsonObject)
}

fun <T> JsonObject.byList(name: String, constructor: (JsonObject) -> T): Lazy<List<T>> = lazy {
    this.getNotNull(name).asJsonArray.map { constructor(it as JsonObject) }.toList()
}