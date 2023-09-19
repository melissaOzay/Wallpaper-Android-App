package com.example.havucwallpapernewversion.helper

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceHelper @Inject constructor(@ApplicationContext private val context: Context) {
    @SuppressLint("HardwareIds")
    fun getDeviceUniqueId(): String {
        val androidId = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        val deviceId = androidId + Build.BOARD + Build.BRAND + Build.DEVICE +
                Build.DISPLAY + Build.HOST + Build.ID + Build.MANUFACTURER +
                Build.MODEL + Build.PRODUCT + Build.TAGS + Build.TYPE + Build.USER

        return generateHash(deviceId)
    }

    private fun generateHash(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(input.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}