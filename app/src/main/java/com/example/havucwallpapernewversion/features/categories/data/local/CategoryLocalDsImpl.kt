package com.example.havucwallpapernewversion.features.categories.data.local

import com.example.havucwallpapernewversion.data.local.db.category.dao.CategoryDAO
import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryLocalDSImpl @Inject constructor(
    private val categoryDAO: CategoryDAO,
) : CategoryLocalDS {

    override fun getCategories(): Flow<List<CategoryEntity>> {
        return categoryDAO.getCategories()
    }

    override suspend fun insertCategory(categoryEntity: CategoryEntity) {
        return categoryDAO.insert(categoryEntity)
    }
}