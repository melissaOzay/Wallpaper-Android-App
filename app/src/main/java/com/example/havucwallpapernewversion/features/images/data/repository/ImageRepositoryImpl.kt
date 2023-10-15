package com.example.havucwallpapernewversion.features.images.data.repository

import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import com.example.havucwallpapernewversion.features.images.data.local.ImageLocalDS
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
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

        } catch (ex: Exception) {
            var images = ArrayList<Image>()
            imageList(null,images)
            Result.success(images)
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
            var images = ArrayList<Image>()
            imageList(query,images)
            Result.success(images)
        }
    }

    private fun imageList(query: String?, imageList: ArrayList<Image>) {
        imageList.apply {
            if (query == "Barbie") {
                add(
                    Image(
                        "123",
                        "https://img.freepik.com/premium-photo/barbie-doll-wallpapers-iphone-android-download-app-iphone-android_349618-2640.jpg",
                        "https://img.freepik.com/premium-photo/barbie-doll-wallpapers-iphone-android-download-app-iphone-android_349618-2640.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "124",
                        "https://c4.wallpaperflare.com/wallpaper/211/479/623/barbie-dolls-barbies-dolls-girls-wallpaper-preview.jpg",
                        "https://c4.wallpaperflare.com/wallpaper/211/479/623/barbie-dolls-barbies-dolls-girls-wallpaper-preview.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "125",
                        "https://e1.pxfuel.com/desktop-wallpaper/446/477/desktop-wallpaper-barbie-cartoon-barbie-thumbnail.jpg",
                        "https://e1.pxfuel.com/desktop-wallpaper/446/477/desktop-wallpaper-barbie-cartoon-barbie-thumbnail.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "126",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuz3ZvYL2wsuIz2wskTKdIkGqERYuNh20Hcg&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuz3ZvYL2wsuIz2wskTKdIkGqERYuNh20Hcg&usqp=CAU",
                        false
                    )
                )
            } else if (query == "Animal") {
                add(
                    Image(
                        "123",
                        "https://i.pinimg.com/474x/68/a5/3e/68a53eb6bfc8909fdcf792d98dbf457c.jpg",
                        "https://i.pinimg.com/474x/68/a5/3e/68a53eb6bfc8909fdcf792d98dbf457c.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "124",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT659NaxKDiP5TLynyuyTTlOLw37bsucGGSKg&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT659NaxKDiP5TLynyuyTTlOLw37bsucGGSKg&usqp=CAU",
                        false
                    )
                )
                add(
                    Image(
                        "125",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjVKdD67Ulca-TOGc-hHZ8ox3aviyH7Eq0FQ&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjVKdD67Ulca-TOGc-hHZ8ox3aviyH7Eq0FQ&usqp=CAU",
                        false
                    )
                )
                add(
                    Image(
                        "126",
                        "https://images.wallpaperscraft.com/image/single/wolf_predator_howl_134450_168x300.jpg",
                        "https://images.wallpaperscraft.com/image/single/wolf_predator_howl_134450_168x300.jpg",
                        false
                    )
                )
            } else if (query == "Star_wars") {
                add(
                    Image(
                        "131",
                        "https://cdn.wallpapersafari.com/17/32/rFvY91.jpg",
                        "https://cdn.wallpapersafari.com/17/32/rFvY91.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "132",
                        "https://wallpapercosmos.com/w/full/1/7/7/227751-3000x1688-desktop-hd-star-wars-the-clone-wars-wallpaper-photo.jpg",
                        "https://wallpapercosmos.com/w/full/1/7/7/227751-3000x1688-desktop-hd-star-wars-the-clone-wars-wallpaper-photo.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "133",
                        "https://img.freepik.com/premium-photo/star-wars-clone-wars-wallpapers_883586-686.jpg",
                        "https://img.freepik.com/premium-photo/star-wars-clone-wars-wallpapers_883586-686.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "134",
                        "https://i.pinimg.com/originals/3a/81/b6/3a81b659fd69b910fa23c5b03606176a.jpg",
                        "https://i.pinimg.com/originals/3a/81/b6/3a81b659fd69b910fa23c5b03606176a.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "135",
                        "https://images.wallpapersden.com/image/download/star-wars-the-bad-batch-2021_bGttam2UmZqaraWkpJRnZWltrWdlaW0.jpg",
                        "https://images.wallpapersden.com/image/download/star-wars-the-bad-batch-2021_bGttam2UmZqaraWkpJRnZWltrWdlaW0.jpg",
                        false
                    )
                )

            }else if (query.isNullOrEmpty()){
                add(
                    Image(
                        "123",
                        "https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg?w=2000",
                        "https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg?w=2000",
                        false
                    )
                )
                add(
                    Image(
                        "124",
                        "https://marketplace.canva.com/EAE-xnqWvJk/1/0/1600w/canva-retro-smoke-and-round-light-desktop-wallpapers-JLofAI27pCg.jpg",
                        "https://marketplace.canva.com/EAE-xnqWvJk/1/0/1600w/canva-retro-smoke-and-round-light-desktop-wallpapers-JLofAI27pCg.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "125",
                        "https://c4.wallpaperflare.com/wallpaper/39/346/426/digital-art-men-city-futuristic-night-hd-wallpaper-thumb.jpg",
                        "https://c4.wallpaperflare.com/wallpaper/39/346/426/digital-art-men-city-futuristic-night-hd-wallpaper-thumb.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "126",
                        "https://i.pinimg.com/474x/a6/8d/54/a68d5486520ca1cfefc222cd45ba9e2b.jpg",
                        "https://i.pinimg.com/474x/a6/8d/54/a68d5486520ca1cfefc222cd45ba9e2b.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "127",
                        "https://w0.peakpx.com/wallpaper/622/638/HD-wallpaper-move-forward-landscape-lightening-man-neon-night-tecnology-thumbnail.jpg",
                        "https://w0.peakpx.com/wallpaper/622/638/HD-wallpaper-move-forward-landscape-lightening-man-neon-night-tecnology-thumbnail.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "128",
                        "https://i.pinimg.com/236x/4b/05/0c/4b050ca4fcf588eedc58aa6135f5eecf.jpg",
                        "https://i.pinimg.com/236x/4b/05/0c/4b050ca4fcf588eedc58aa6135f5eecf.jpg",
                        false
                    )
                )
                add(
                    Image(
                        "129",
                        "https://wallpapers.com/images/featured/beautiful-3vau5vtfa3qn7k8v.jpg",
                        "https://wallpapers.com/images/featured/beautiful-3vau5vtfa3qn7k8v.jpg",
                        false
                    )
                )
            }

        }
        Result.success(imageList)
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
        return imageLocalDS.getImagesSingle().map {
            it.toImage()
        }
    }


}

