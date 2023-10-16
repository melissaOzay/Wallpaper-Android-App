package com.example.havucwallpapernewversion.features.images.data.local

import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.domain.model.Image

interface ImageLocalDS {
    fun getImagesObservable(): List<ImageEntity>
    fun removeImage(imageId: String)
    fun addImage(imageEntity: ImageEntity)
    fun deleteImage(imageId: String)
    fun isImageFavori(imageId: String): Boolean
    fun setCurrentImage(image: Image)
    fun getCurrentImage(): Image
}