package com.example.havucwallpapernewversion.data.local

interface LocalDS {
    suspend fun saveAuthorizationKey(key: String)
    fun getAuthorizationKey(): String
}