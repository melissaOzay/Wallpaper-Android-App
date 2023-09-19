package com.example.havucwallpapernewversion.features.account.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    @SerialName("authozationKey") val authozationKey: String,
    @SerialName("isAllowNotification") val isAllowNotification: Boolean,
    @SerialName("udid") val udid: String,
    @SerialName("userId") val userId: String
)