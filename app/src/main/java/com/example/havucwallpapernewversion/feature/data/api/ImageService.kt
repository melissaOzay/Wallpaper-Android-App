package com.example.havucwallpapernewversion.feature.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("api/images")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("imageType") imageType: String,
    ): NetworkResult<BaseResponse<List<ImageResponse>>>

    @GET("api/images")
    suspend fun searchImages(
        @Query("page") page: Int,
        @Query("search-query") query: String,
        @Query("imageType") imageType: String,
    ): NetworkResult<BaseResponse<List<ImageResponse>>>

}