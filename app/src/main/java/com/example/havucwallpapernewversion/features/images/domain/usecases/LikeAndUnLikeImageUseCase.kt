package com.example.havucwallpapernewversion.features.images.domain.usecases

import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import javax.inject.Inject

class LikeAndUnLikeImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
     operator fun invoke(image: Image) {
        imageRepository.likeAndUnlikeImage(image)
    }
}