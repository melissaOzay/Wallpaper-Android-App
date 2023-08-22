package com.example.havucwallpapernewversion

interface CoreLocalHelper {
    fun saveAuthorizationToken(authorizationToken: String)
    fun getAuthorizationToken(): String?
}