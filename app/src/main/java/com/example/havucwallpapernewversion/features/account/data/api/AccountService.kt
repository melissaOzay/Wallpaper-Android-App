package com.example.havucwallpapernewversion.features.account.data.api

import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("api/accounts")
    suspend fun registerUser(@Body registerUserRequest: RegisterUserRequest): BaseResponse<RegisterUserResponse>
}