package com.example.havucwallpapernewversion.features.categories.data.repository

import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDs
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDS
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategory
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategoryEntity
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDs: CategoryRemoteDS,
    private val categoryLocalDs: CategoryLocalDs
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = categoryRemoteDs.getCategories()
            val localResponse = categoryLocalDs.getCategories()
            if (localResponse.isEmpty()) {
                response.data.map {
                    categoryLocalDs.insertCategories(it.toCategoryEntity())
                }
            }
            Result.success(localResponse.map {
                it.toCategory()
            })
        } catch (ex: java.lang.Exception) {
            System.out.println("Error desc: " + ex.message)
            Result.failure(Exception(ex))
        }
    }
}