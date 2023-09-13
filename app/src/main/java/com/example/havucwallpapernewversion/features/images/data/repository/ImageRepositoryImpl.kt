package com.example.havucwallpapernewversion.features.images.data.repository

import android.util.Log
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.data.local.ImageLocalDS
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS,
    private val imageLocalDS: ImageLocalDS
) : ImageRepository {
    override suspend fun getImages(page: Int): Result<List<Image>> {
        return try {
            val response = imageRemoteDS.getImages(page)
            val images = response.data.map {
                val isFavori = imageLocalDS.isImageFavori(it.imageId.toString())
                it.toImage(isFavori)
            }
            Result.success(images)
        } catch (ex: java.lang.Exception) {
            System.out.println("Error desc: " + ex.message)
            Result.failure(Exception(ex))
        }
    }

    override fun likeAndUnlikeImage(image: Image) {
        return try {
            val isImageFavorite = imageLocalDS.isImageFavori(image.id)
            if (isImageFavorite.not()) {
                val insertImageEntity = ImageEntity(
                    imageId = image.id,
                    imagePath = image.path,
                    imageFullPath = image.imagePullPath
                )
                imageLocalDS.addImage(insertImageEntity)
            } else {
                imageLocalDS.removeImage(image.id)
            }
        } catch (ex: java.lang.Exception) {
            System.out.println("Error desc: " + ex.message)
        }
    }

    override suspend fun getFavoriteImages(): List<Image> {
       return imageLocalDS.getImagesSingle().map {
            it. toImage()}
    }
}

