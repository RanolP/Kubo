package io.github.ranolp.kubo.general.objects

import io.github.ranolp.kubo.general.side.Sided

interface User : Sided{
    val displayName: String
    val isSelf: Boolean
}