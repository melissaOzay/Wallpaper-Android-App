package com.example.havucwallpapernewversion.features.categories.data.local

import com.example.havucwallpapernewversion.data.local.db.category.dao.CategoryDAO
import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import javax.inject.Inject

class CategoryLocalDsImpl @Inject constructor(
    private val categoryDAO: CategoryDAO,
) : CategoryLocalDs {

    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryDAO.getCategories()
    }

    override suspend fun insertCategories(categoryEntity: CategoryEntity) {
        return categoryDAO.insert(categoryEntity)
    }
}