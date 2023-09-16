package com.example.havucwallpapernewversion.features.categories.data.local

import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity

interface CategoryLocalDs {
    suspend fun getCategories():List<CategoryEntity>
    suspend fun insertCategories(categoryEntity: CategoryEntity)
}