package io.github.ranolp.kubo.general

import io.github.ranolp.kubo.general.side.Sided

interface User : Sided{
    val displayName: String
}