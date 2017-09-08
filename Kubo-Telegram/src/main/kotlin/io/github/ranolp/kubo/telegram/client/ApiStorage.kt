package io.github.ranolp.kubo.telegram.client

import com.github.badoualy.telegram.api.TelegramApiStorage
import com.github.badoualy.telegram.mtproto.auth.AuthKey
import com.github.badoualy.telegram.mtproto.model.DataCenter
import com.github.badoualy.telegram.mtproto.model.MTSession
import java.nio.file.Files
import java.nio.file.Path

class ApiStorage(val authKeyFile: Path, val nearestDcFile: Path) : TelegramApiStorage {
    override fun saveAuthKey(authKey: AuthKey) {
        Files.write(authKeyFile, authKey.key)
    }

    override fun loadAuthKey(): AuthKey? {
        return try {
            AuthKey(Files.readAllBytes(authKeyFile))
        } catch(e: Throwable) {
            null
        }
    }

    override fun deleteAuthKey() {
        Files.deleteIfExists(authKeyFile)
    }

    override fun saveDc(dataCenter: DataCenter) {
        Files.write(nearestDcFile, dataCenter.toString().toByteArray())
    }

    override fun loadDc(): DataCenter? {
        return try {
            val infos = Files.readAllBytes(authKeyFile).toString().split(":")
            DataCenter(infos[0], infos[1].toInt())
        } catch(e: Throwable) {
            null
        }
    }

    override fun deleteDc() {
        Files.deleteIfExists(nearestDcFile)
    }

    override fun loadSession(): MTSession? = null


    override fun saveSession(session: MTSession?) {
        // do nothing
    }
}