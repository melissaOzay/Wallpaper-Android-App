package com.example.havucwallpapernewversion.features.account.data.local

import com.example.havucwallpapernewversion.data.local.LocalDS
import javax.inject.Inject

class AccountLocalDSImpl @Inject constructor(
    private val localDS: LocalDS,
) : AccountLocalDS {

    override suspend fun saveAuthorizationKey(key: String) {
        localDS.saveAuthorizationKey(key)
    }

    override fun getAuthorizationKey(): String {
        return "xWBHGCTNb6580aL"
    }
}