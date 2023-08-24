package com.example.havucwallpapernewversion.features.account.domain.mapper

import com.example.havucwallpapernewversion.features.account.data.model.response.RegisterUserResponse
import com.example.havucwallpapernewversion.features.account.domain.entity.User

fun RegisterUserResponse.toUser() = User(
    this.userId
)