package com.example.havucwallpapernewversion.features.account.data.repository

import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.domain.entity.User

interface AccountRepository {
    suspend fun registerUser(userRequest: RegisterUserRequest): Result<User>

}