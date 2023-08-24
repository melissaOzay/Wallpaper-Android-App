package com.example.havucwallpapernewversion.features.account.data.local

interface AccountLocalDS {
    suspend fun saveAuthorizationKey(key: String)
    fun getAuthorizationKey(): String
}