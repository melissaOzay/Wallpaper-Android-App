package com.example.havucwallpapernewversion.features.images.data.repository

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageBaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import retrofit2.Response
interface ImageRepository {
    suspend fun getImages(page: Int):Result<BaseResponse<List<ImageResponse>>>

}