package com.example.havucwallpapernewversion.features.categories.data.repository

import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<Result<List<Category>>>
}