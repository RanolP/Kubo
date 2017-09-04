package io.github.ranolp.kubo.general.side

class Side(val name: String, val isBot: Boolean) {
    companion object {
        val ANY = Side("Any Side", false)
    }
}