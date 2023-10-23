package com.example.havucwallpapernewversion.features.categories.data.local

import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDS {
    fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun insertCategory(categoryEntity: CategoryEntity)
}