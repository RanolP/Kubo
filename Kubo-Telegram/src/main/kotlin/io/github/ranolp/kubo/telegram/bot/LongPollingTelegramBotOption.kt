package io.github.ranolp.kubo.telegram.bot

import io.github.ranolp.kubo.telegram.bot.objects.Update

class LongPollingTelegramBotOption(botName: String, token: String, val timeout: Int? = null, val daemon: Boolean = false, vararg val updateType: Update.Type) : TelegramBotOption(botName, token) {}