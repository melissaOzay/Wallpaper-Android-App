package com.example.havucwallpapernewversion.features.images.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageBaseResponse
import retrofit2.Response
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse

interface ImageRemoteDS {
    suspend fun getImages(page: Int): BaseResponse<List<ImageResponse>>
}