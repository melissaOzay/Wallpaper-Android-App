package com.example.havucwallpapernewversion.features.images.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse

interface ImageRemoteDS {
    suspend fun getImages(page: Int): BaseResponse<List<ImageResponse>>
    suspend fun getSearchImages(page: Int,query:String): BaseResponse<List<ImageResponse>>
}