package com.example.havucwallpapernewversion.features.categories.domain.mapper

import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.categories.data.model.CategoryResponse
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.features.images.domain.model.Image

fun CategoryEntity.toCategory() = Category(
    categoryQuery = query,
    count = count,
    categoryTitle = title,
    image = thumbnail
)
fun CategoryResponse.toCategoryEntity() = CategoryEntity(
    id = 0,
    query = categoryQuery,
    title = categoryTitle,
    thumbnail = imagePath,
    count = categoryImageCount
)
