package com.example.havucwallpapernewversion.features.account.data.repository

import android.database.CursorJoiner
import android.util.Log
import android.widget.Toast
import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDS
import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDS
import com.example.havucwallpapernewversion.features.account.domain.entity.User
import com.example.havucwallpapernewversion.features.account.domain.mapper.toUser
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDS: AccountRemoteDS,
    private val accountLocalDS: AccountLocalDS,
) : AccountRepository {


    override suspend fun registerUser(userRequest: RegisterUserRequest): Result<User> {
        val response = accountRemoteDS.registerUser(userRequest)
        return if (response.data != null) {
            accountLocalDS.saveAuthorizationKey(response.data.authozationKey)
            Result.success(response.data.toUser())
        } else {
            Result.failure(Exception("user hatalı"))
        }

        /*response.getResult {
                accountLocalDS.saveAuthorizationKey(it.data.authozationKey)
                it.data.toUser()
            }*/
    }
}