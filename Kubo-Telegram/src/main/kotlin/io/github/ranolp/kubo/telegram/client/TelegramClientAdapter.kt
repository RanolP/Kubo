package io.github.ranolp.kubo.telegram.client

import com.github.badoualy.telegram.api.Kotlogram
import com.github.badoualy.telegram.api.TelegramApp
import com.github.badoualy.telegram.api.TelegramClient
import io.github.ranolp.kubo.KuboAdapter
import io.github.ranolp.kubo.general.objects.User
import io.github.ranolp.kubo.telegram.Telegram
import io.github.ranolp.kubo.telegram.client.objects.TelegramClientUser

class TelegramClientAdapter(option: TelegramClientOption) : KuboAdapter<TelegramClientOption>(option,
        Telegram.CLIENT_SIDE) {
    private lateinit var client: TelegramClient
    private lateinit var _myself: TelegramClientUser
    override val myself: User
        get() = _myself

    override fun login() {
        val app = TelegramApp(option.apiId,
                option.apiHash,
                option.deviceModel,
                option.systemVersion,
                option.appVersion,
                option.langCode)
        client = Kotlogram.getDefaultClient(app, ApiStorage(option.authKeyFile, option.nearestDcFile))
        val sentCode = client.authSendCode(false, option.phoneNumber, true)
        print("Requires sent code: ")
        val auth = client.authSignIn(option.phoneNumber, sentCode.phoneCodeHash, readLine())
        _myself = TelegramClientUser(auth.user.asUser)
        TODO("not implemented")
    }

    override fun logout() {
        TODO("not implemented")
    }
}