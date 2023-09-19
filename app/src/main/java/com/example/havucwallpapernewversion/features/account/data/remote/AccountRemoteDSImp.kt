package com.example.havucwallpapernewversion.features.account.data.remote

import com.example.havucwallpapernewversion.features.account.data.api.AccountService
import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse
import retrofit2.Retrofit
import javax.inject.Inject

class AccountRemoteDSImp @Inject constructor(
    private val retrofit: Retrofit,
) : AccountRemoteDS {
    private val accountService by lazy {
        retrofit.create(AccountService::class.java)
    }

    override suspend fun registerUser(userRequest: RegisterUserRequest): BaseResponse<RegisterUserResponse> =
        accountService.registerUser(userRequest)
}