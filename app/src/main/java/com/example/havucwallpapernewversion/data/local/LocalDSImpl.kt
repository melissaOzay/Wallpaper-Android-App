package com.example.havucwallpapernewversion.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LocalDSImpl @Inject constructor(private val context: Context) : LocalDS {
    private val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "app"
    )
    private val AUTHORIZATION_KEY = stringPreferencesKey("authorizationKey")
    override suspend fun saveAuthorizationKey(key: String) {
        context.appPreferencesDataStore.edit {
            it[AUTHORIZATION_KEY] = key
        }
    }

    override fun getAuthorizationKey(): String {
        return "xWBHGCTNb6580aL"
    }
}