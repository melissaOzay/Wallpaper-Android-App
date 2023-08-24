package com.example.havucwallpapernewversion.features.images.data.repository

import android.media.Image
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS,
) : ImageRepository {
    override suspend fun getImages(page: Int): BaseResponse<List<ImageResponse>> {
        return withContext(Dispatchers.IO) {
            return@withContext imageRemoteDS.getImages(page)
        }
    }
}