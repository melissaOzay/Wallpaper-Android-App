package com.example.havucwallpapernewversion.features.images.data.repository

import android.content.Context
import android.util.Log
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.data.local.ImageLocalDS
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.ui.images.ImagesFragment
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDS: ImageRemoteDS,
    private val imageLocalDS: ImageLocalDS
) : ImageRepository {
    override suspend fun getImages(page: Int): Result<List<Image>> {
        return try {
            val response = imageRemoteDS.getImages(page)
            val images = response.data.map {
                val isFavori = imageLocalDS.isImageFavori(it.imageId.orEmpty())
                it.toImage(isFavori)
            }
            Result.success(images)

        } catch (ex: HttpException) {
            val response = ex.response()?.errorBody()?.string()
            val getBaseResponse = JSONObject(response.orEmpty())
            Result.failure(Throwable(getBaseResponse.getString("error")))
           // Result.success(getMockImages(""))
        }
    }

    override suspend fun getSearchImages(page: Int, query: String): Result<List<Image>> {
        return try {
            val response = imageRemoteDS.getSearchImages(page, query)
            val images = response.data.map {
                val isFavori = imageLocalDS.isImageFavori(it.imageId.orEmpty())
                it.toImage(isFavori)
            }
            Result.success(images)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun isLiked(id: String): Boolean {
        return imageLocalDS.isImageFavori(id)
    }

    private fun getMockImages(query: String?): ArrayList<Image> {
        val jsonLocation = readJsonFromAssets("image.json")
        val jsonobject = JSONObject(jsonLocation)
        val getImages = ArrayList<Any>()
        val jsonList = jsonobject.getJSONArray(query) as JSONArray
        for (i in 0 until jsonList.length()) {
            val jb = jsonList[i] as JSONObject
            val id = jb.getString("id")
            val imagePullPath = jb.getString("imagePullPath")
            val path = jb.getString("path")
            val liked = isLiked(id)

            val images = Image(id, path, imagePullPath, liked)
            getImages.add(images)

        }
        return getImages as ArrayList<Image>
    }

    private fun readJsonFromAssets(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("assets/$fileName")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return stringBuilder.toString()
    }

    override suspend fun likeAndUnlikeImage(image: Image) {
        return try {
            val isImageFavorite = imageLocalDS.isImageFavori(image.id)
            if (!isImageFavorite) {
                val insertImageEntity = ImageEntity(
                    imageId = image.id,
                    imagePath = image.path,
                    imageFullPath = image.imagePullPath
                )
                imageLocalDS.addImage(insertImageEntity)
            } else {
                imageLocalDS.removeImage(image.id)
            }
        } catch (ex: Exception) {

        }
    }

    override suspend fun getFavoriteImages(): List<Image> {
        return imageLocalDS.getImagesObservable().map {
            it.toImage()
        }
    }


}

