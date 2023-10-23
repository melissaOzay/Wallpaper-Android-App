package com.example.havucwallpapernewversion.features.categories.data.api

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.categories.data.model.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET("api/images/categories")
     suspend fun getCategories(
        @Query("imageType") imageType: String
    ): BaseResponse<List<CategoryResponse>>
}