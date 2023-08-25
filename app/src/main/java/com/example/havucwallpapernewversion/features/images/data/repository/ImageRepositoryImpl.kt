package com.example.havucwallpapernewversion.features.images.data.repository

import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import javax.inject.Inject
import retrofit2.Response

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS,
) : ImageRepository {
    override suspend fun getImages(page: Int): Response<List<ImageResponse>> {
        val response = imageRemoteDS.getImages(page)
        return if (response.body()!!.isNotEmpty()) {
            Response.success(response.body())
        } else {
            Response.error(response.code(),response.errorBody())
        }
    }
}