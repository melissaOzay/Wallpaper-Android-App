package com.example.havucwallpapernewversion.data.local.shared

interface CoreLocalHelper {
    fun saveAuthorizationToken(authorizationToken: String)
    fun getAuthorizationToken(): String?
}