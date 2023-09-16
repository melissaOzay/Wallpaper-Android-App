package com.example.havucwallpapernewversion.features.categories.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("categoryImageCount")
    val categoryImageCount: Int,
    @SerialName("categoryTitle")
    val categoryTitle: String,
    @SerialName("categoryQuery")
    val categoryQuery: String,
    @SerialName("imagePath")
    val imagePath: String,
)