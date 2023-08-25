package com.example.havucwallpapernewversion.features.images.data.remote

import retrofit2.Response
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse

interface ImageRemoteDS {
    suspend fun getImages(page: Int): Response<List<ImageResponse>>
}