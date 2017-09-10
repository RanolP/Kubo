package io.github.ranolp.kubo.general.side

class Side(val name: String, val isBot: Boolean, val isClient: Boolean) {
    companion object {
        val ANY = Side("Any Side", true, true)
    }

    override fun toString(): String {
        return name
    }
}