package com.example.havucwallpapernewversion.features.account.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(
    val pnsToken: String,
    val udid: String
)