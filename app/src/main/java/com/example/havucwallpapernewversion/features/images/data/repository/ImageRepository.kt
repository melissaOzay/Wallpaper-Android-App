package com.example.havucwallpapernewversion.features.images.data.repository

import android.media.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImages(page: Int): Result<List<Image>>

}