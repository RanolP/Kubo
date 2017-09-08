package io.github.ranolp.kubo.telegram.client

import io.github.ranolp.kubo.general.Option
import io.github.ranolp.kubo.telegram.Telegram
import java.nio.file.Path
import java.nio.file.Paths

class TelegramClientOption(val apiId: Int, val apiHash: String, val phoneNumber: String,
        val appVersion: String = "Kubo Based App", val deviceModel: String = "Unknown",
        val systemVersion: String = "Unknown", val langCode: String = "EN",
        val authKeyFile: Path = Paths.get("auth_key"),
        val nearestDcFile: Path = Paths.get("nearest_data_center")) : Option(Telegram.CLIENT_SIDE) {}