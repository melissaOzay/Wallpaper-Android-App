package com.example.havucwallpapernewversion.features.images.data.repository

import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS,
) : ImageRepository {
    override suspend fun getImages(page: Int): Result<List<ImageResponse>> {
        val response = imageRemoteDS.getImages(page)
        return if (response.data != null) {
        Result.success(response.data)
        } else {
            Result.failure(Exception("image hatalı"))
        }
    }
}