package com.example.havucwallpapernewversion.features.images.data.api

import com.example.havucwallpapernewversion.features.images.data.model.ImageBaseResponse
import retrofit2.Response
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("api/images")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("imageType") imageType: String,
    ): Response<ImageBaseResponse>

    @GET("api/images")
    suspend fun searchImages(
        @Query("page") page: Int,
        @Query("search-query") query: String,
        @Query("imageType") imageType: String,
    ): Response<List<ImageResponse>>

}