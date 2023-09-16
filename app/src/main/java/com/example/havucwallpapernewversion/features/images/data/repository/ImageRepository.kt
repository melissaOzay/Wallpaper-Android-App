package com.example.havucwallpapernewversion.features.images.data.repository

import com.example.havucwallpapernewversion.features.images.domain.model.Image

interface ImageRepository {
    suspend fun getImages(page: Int): Result<List<Image>>
    fun likeAndUnlikeImage(image: Image)
    suspend fun getFavoriteImages(): List<Image>
    suspend fun getSearchImages(page: Int,query:String): Result<List<Image>>


}