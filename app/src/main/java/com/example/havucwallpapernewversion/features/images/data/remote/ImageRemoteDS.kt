package com.example.havucwallpapernewversion.features.images.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.utility.NetworkResult

interface ImageRemoteDS {
    suspend fun getImages(page: Int): NetworkResult<BaseResponse<List<ImageResponse>>>
}