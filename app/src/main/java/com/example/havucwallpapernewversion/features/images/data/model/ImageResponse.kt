package com.example.havucwallpapernewversion.features.images.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName("imageId")
    val imageId: String,
    @SerialName("imagePath")
    val imagePath: String,
    @SerialName("imagePullPath")
    val imagePullPath: String,

    )
