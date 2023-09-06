package com.example.havucwallpapernewversion.features.account.data.repository

import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse
import com.example.havucwallpapernewversion.features.account.domain.entity.User
import retrofit2.Response

interface AccountRepository {
    suspend fun registerUser(userRequest: RegisterUserRequest): Result<BaseResponse<RegisterUserResponse>>

}