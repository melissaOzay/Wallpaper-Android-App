package com.example.havucwallpapernewversion.features.images.domain.mapper

import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.model.Image

fun ImageResponse.toImage(isLiked: Boolean) = Image(
    id = imageId.orEmpty(),
    path = imagePath.orEmpty(),
    imagePullPath = imagePullPath.orEmpty(),
    isLiked = isLiked
)

fun ImageEntity.toImage() = Image(
    id = imageId,
    path = imagePath,
    imagePullPath = imageFullPath,
    isLiked = true
)



