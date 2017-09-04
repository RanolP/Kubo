package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.Property
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.valueParameters

class KuboMapProperty<T>(private val name: String?, private val get: (String) -> T, private val set: (String, T) -> Any?) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = get(name ?: property.name)
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = set(name ?: property.name, value)
}

inline fun <reified T> MutableMap<in String, in Any?>.mapping(name: String? = null): KuboMapProperty<T> {
    val classOfT = T::class
    val isProperty = Property::class in classOfT.allSuperclasses
    val constructor by lazy {
        classOfT.constructors.filter {
            it.valueParameters.let { (it[0].type.classifier as KClass<*>).isSubclassOf(Map::class) && it.size == 1 }
        }.first()
    }
    return KuboMapProperty<T>(name, {
        val got = get(it)
        val e = when (got) {
            is Number -> {
                when (classOfT.java) {
                    Byte::class.javaObjectType -> got.toByte()
                    Short::class.javaObjectType -> got.toShort()
                    Int::class.javaObjectType -> got.toInt()
                    Long::class.javaObjectType -> got.toLong()
                    Float::class.javaObjectType -> got.toFloat()
                    Double::class.javaObjectType -> got.toDouble()
                    else -> TODO("replace it to custom logError class")
                }
            }
            is Map<*, *> -> {
                if (isProperty) {
                    constructor.call(got)
                } else got
            }
            else -> got
        }
        return@KuboMapProperty e as T
    }, this::put)
}