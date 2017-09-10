package io.github.ranolp.kubo

import io.github.ranolp.kubo.general.Option
import io.github.ranolp.kubo.general.side.Side
import java.util.*

object Adapters {
    private val adapterMap = HashMap<Side, KuboAdapter<Option>>()

    internal fun put(side: Side, kuboAdapter: KuboAdapter<Option>) {
        adapterMap[side] = kuboAdapter
    }

    fun getAdapter(side: Side) = adapterMap[side]

    fun all() = adapterMap.values

    inline fun <reified T : KuboAdapter<Option>> getTypedAdapter(side: Side) = getAdapter(side) as? T
}