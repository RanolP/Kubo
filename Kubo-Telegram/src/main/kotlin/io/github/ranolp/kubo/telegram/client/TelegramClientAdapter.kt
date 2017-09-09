package io.github.ranolp.kubo.telegram.client

import com.github.badoualy.telegram.api.Kotlogram
import com.github.badoualy.telegram.api.TelegramApp
import com.github.badoualy.telegram.api.TelegramClient
import io.github.ranolp.kubo.KuboAdapter
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.client.objects.TelegramClientUser
import org.slf4j.impl.SimpleLogger

class TelegramClientAdapter(option: TelegramClientOption) : KuboAdapter<TelegramClientOption>(option,
        Telegram.CLIENT_SIDE) {
    private lateinit var _client: TelegramClient
    private lateinit var _myself: TelegramClientUser
    val client: TelegramClient
        get() = _client
    override val myself: User
        get() = _myself

    override fun login() {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR")
        val app = TelegramApp(option.apiId,
                option.apiHash,
                option.deviceModel,
                option.systemVersion,
                option.appVersion,
                option.langCode)
        _client = Kotlogram.getDefaultClient(app,
                ApiStorage(option.authKeyFile, option.nearestDcFile),
                updateCallback = TelegramUpdateCallback)
        val sentCode = _client.authSendCode(false, option.phoneNumber, true)
        print("Requires sent code: ")
        val auth = _client.authSignIn(option.phoneNumber, sentCode.phoneCodeHash, readLine())
        _myself = TelegramClientUser(auth.user.asUser)
    }

    override fun logout() {
        _client.close()
        Kotlogram.shutdown()
    }
}