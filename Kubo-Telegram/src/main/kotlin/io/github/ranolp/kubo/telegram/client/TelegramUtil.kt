package io.github.ranolp.kubo.telegram.client

import java.util.*

private val rand = Random()

fun newLongId() = Math.abs(rand.nextLong())