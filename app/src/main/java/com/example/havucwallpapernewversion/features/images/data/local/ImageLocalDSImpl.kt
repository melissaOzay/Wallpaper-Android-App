package com.example.havucwallpapernewversion.features.images.data.local

import com.example.havucwallpapernewversion.data.local.db.image.dao.ImageDAO
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLocalDSImpl @Inject constructor(
    private val imageDAO: ImageDAO,
) : ImageLocalDS {
    private lateinit var image: Image
    override fun getImagesObservable():List<ImageEntity> = imageDAO.getImages()

    override fun removeImage(imageId: String) = imageDAO.deleteImage(imageId)

    override suspend fun getImagesSingle(): List<ImageEntity> = imageDAO.getImagesSingle()

    override fun addImage(imageEntity: ImageEntity) {
        imageDAO.insert(imageEntity)
    }

    override fun deleteImage(imageId: String) = imageDAO.deleteImage(imageId)
    override fun isImageFavori(imageId: String): Boolean = imageDAO.isImageFavori(imageId)
    override fun setCurrentImage(image: Image) {
        this.image = image
    }

    override fun getCurrentImage(): Image = image
}