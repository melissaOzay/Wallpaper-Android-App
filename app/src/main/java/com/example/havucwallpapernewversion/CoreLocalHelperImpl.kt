package com.example.havucwallpapernewversion

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class CoreLocalHelperImpl @Inject constructor(context: Context) : CoreLocalHelper  {
    private val AUTHORIZATON_PREF_HELPER = "authorizationPref"
    lateinit var mPrefs: SharedPreferences
    lateinit var mPref: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
        mPref = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
    }

    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, authorizationToken).apply()
    }

    override fun getAuthorizationToken(): String? {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "UnAuthorization")!!
    }

}