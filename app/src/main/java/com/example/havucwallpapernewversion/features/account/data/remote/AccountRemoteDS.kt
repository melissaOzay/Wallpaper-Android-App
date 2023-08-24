package com.example.havucwallpapernewversion.features.account.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse

interface AccountRemoteDS {
    suspend fun registerUser(userRequest: RegisterUserRequest): BaseResponse<RegisterUserResponse>
}