package com.example.havucwallpapernewversion.features.images.data.remote

import retrofit2.Response
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import com.example.havucwallpapernewversion.features.images.data.model.ImageBaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import retrofit2.Retrofit
import javax.inject.Inject

class ImageRemoteDSImpl @Inject constructor(
    private val retrofit: Retrofit,
) : ImageRemoteDS {

    private val imageService by lazy {
        retrofit.create(ImageService::class.java)
    }
    override suspend fun getImages(page: Int): Response<ImageBaseResponse> {
        return imageService.getImages(page,"AI_GENERATED")
    }
}
