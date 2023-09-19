package com.example.havucwallpapernewversion.features.images.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import retrofit2.Retrofit
import javax.inject.Inject

class ImageRemoteDSImpl @Inject constructor(
    private val retrofit: Retrofit,
) : ImageRemoteDS {

    private val imageService by lazy {
        retrofit.create(ImageService::class.java)
    }
    override suspend fun getImages(page: Int):BaseResponse<List<ImageResponse>>{
        return imageService.getImages(page,"AI_GENERATED")
    }
    override suspend fun getSearchImages(page: Int,query:String):BaseResponse<List<ImageResponse>>{
        return imageService.searchImages(page,query,"AI_GENERATED")
    }
}
