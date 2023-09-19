package com.example.havucwallpapernewversion.features.account.data.repository

import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDS
import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDS
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDS: AccountRemoteDS,
    private val accountLocalDS: AccountLocalDS,
) : AccountRepository {


    override suspend fun registerUser(userRequest: RegisterUserRequest): Result<BaseResponse<RegisterUserResponse>> {
        return try {
            val response = accountRemoteDS.registerUser(userRequest)
            accountLocalDS.saveAuthorizationKey(response.data.authozationKey)
            Result.success(response)
        } catch (ex: Exception) {
            Result.failure(Exception(ex))
        }
    }

}