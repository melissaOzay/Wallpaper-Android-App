package com.example.havucwallpapernewversion.features.account.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("data")
    val data: T,
    @SerialName("code")
    val code: Int,
    @SerialName("error")
    val error: String,
)
