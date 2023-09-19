package com.example.havucwallpapernewversion.features.categories.data.repository

import android.util.Log
import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDS
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDS
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategory
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategoryEntity
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDS: CategoryRemoteDS,
    private val categoryLocalDS: CategoryLocalDS
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val localCategories = categoryLocalDS.getCategories()
            if (localCategories.isEmpty()) {
                val response = categoryRemoteDS.getCategories()
                response.data.map {
                    categoryLocalDS.insertCategory(it.toCategoryEntity())
                }
            }
            Result.success(localCategories.map {
                it.toCategory()
            })
        } catch (ex: Exception) {
            Log.e("Error desc: " , ex.message.toString())
            Result.failure(Exception(ex))
        }
    }
}