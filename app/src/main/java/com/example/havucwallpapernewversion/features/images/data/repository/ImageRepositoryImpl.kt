package com.example.havucwallpapernewversion.features.images.data.repository

import android.util.Log
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS
) : ImageRepository {
    override suspend fun getImages(page: Int): Result<BaseResponse<List<ImageResponse>>> {
        return try {
            val response = imageRemoteDS.getImages(page)
            Result.success(response)
        } catch (ex: java.lang.Exception) {
            System.out.println("Error desc: " + ex.message)
            Result.failure(Exception(ex))
        }
    }
}