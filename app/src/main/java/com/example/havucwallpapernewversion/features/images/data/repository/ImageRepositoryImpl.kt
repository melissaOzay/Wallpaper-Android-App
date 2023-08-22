package com.example.havucwallpapernewversion.features.images.data.repository

import android.media.Image
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageService
) : ImageRepository {
    override suspend fun getImages(page: Int): Result<List<Image>> {
        return withContext(Dispatchers.IO) {
            return@withContext getImages(page)
        }
    }
}