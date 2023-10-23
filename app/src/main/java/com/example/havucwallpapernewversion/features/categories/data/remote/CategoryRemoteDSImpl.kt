package com.example.havucwallpapernewversion.features.categories.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.categories.data.api.CategoryService
import com.example.havucwallpapernewversion.features.categories.data.model.CategoryResponse
import retrofit2.Retrofit
import javax.inject.Inject

class CategoryRemoteDSImpl @Inject constructor(
    private val retrofit: Retrofit,
) : CategoryRemoteDS {
    private val categoryService by lazy {
        retrofit.create(CategoryService::class.java)
    }

    override suspend fun getCategories(): BaseResponse<List<CategoryResponse>> {
        return categoryService.getCategories("AI_GENERATED")
    }

}