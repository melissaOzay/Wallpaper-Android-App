package com.example.havucwallpapernewversion.features.images.data.model

import com.google.gson.annotations.SerializedName

data class ImageBaseResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: List<ImageResponse>,
    @SerializedName("error") val error: String
)
