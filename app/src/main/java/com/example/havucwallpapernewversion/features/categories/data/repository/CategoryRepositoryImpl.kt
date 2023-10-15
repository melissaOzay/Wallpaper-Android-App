package com.example.havucwallpapernewversion.features.categories.data.repository

import android.util.Log
import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDS
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDS
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategory
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategoryEntity
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.features.images.domain.model.Image
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
            var categories=ArrayList<Category>()
            categoryList(categories)
            Result.success(categories)
        }
    }
    private fun categoryList(imageList: ArrayList<Category>) {
        imageList.apply {
            add(
                Category(
                    "123",
                    "Animal",
                    4,
                    "https://www.wallsauce.com/images/hero/cat/12/645/animal-mural.jpg"
                )
            )
            add(
                Category(
                    "124",
                    "Barbie",
                    4,
                    "https://i.pinimg.com/236x/54/2a/8e/542a8e4edfd465380e7203f4db2e29b8.jpg"
                )
            )
            add(
                Category(
                    "125",
                    "Star_wars",
                    5,
                    "https://i.pinimg.com/originals/3a/81/b6/3a81b659fd69b910fa23c5b03606176a.jpg"
                )
            )
        }
        Result.success(imageList)
    }
}