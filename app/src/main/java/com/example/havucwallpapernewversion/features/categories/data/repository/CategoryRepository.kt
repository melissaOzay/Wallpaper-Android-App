package com.example.havucwallpapernewversion.features.categories.data.repository

import com.example.havucwallpapernewversion.features.categories.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories():Result<List<Category>>
}