package com.example.havucwallpapernewversion.features.categories.data.remote

import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.categories.data.model.CategoryResponse

interface CategoryRemoteDS {
     suspend fun getCategories():BaseResponse<List<CategoryResponse>>
}