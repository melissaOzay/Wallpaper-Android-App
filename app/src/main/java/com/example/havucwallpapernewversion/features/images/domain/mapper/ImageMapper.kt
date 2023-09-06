package com.example.havucwallpapernewversion.features.images.domain.mapper

import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.model.Image

fun ImageResponse.toImage() = Image(
    id = imageId.toString(),
    path = imagePath.toString(),
    imagePullPath = imagePullPath.toString()
)

