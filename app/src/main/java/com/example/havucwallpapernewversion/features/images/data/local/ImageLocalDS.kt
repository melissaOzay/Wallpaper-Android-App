package com.example.havucwallpapernewversion.features.images.data.local

import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageLocalDS {
    fun getImagesObservable(): Flow<List<ImageEntity>>
    fun removeImage(imageId: String)
    suspend fun getImagesSingle(): List<ImageEntity>
    fun addImage(imageEntity: ImageEntity)
    fun deleteImage(imageId: String)
    fun isImageFavori(imageId: String): Boolean

    fun setCurrentImage(image: Image)
    fun getCurrentImage(): Image
}