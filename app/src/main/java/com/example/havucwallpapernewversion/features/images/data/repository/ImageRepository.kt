package com.example.havucwallpapernewversion.features.images.data.repository

import android.media.Image
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
interface ImageRepository {
    suspend fun getImages(page: Int): Response<List<ImageResponse>>

}