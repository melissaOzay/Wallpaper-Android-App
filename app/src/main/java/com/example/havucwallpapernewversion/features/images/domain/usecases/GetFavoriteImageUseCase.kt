package com.example.havucwallpapernewversion.features.images.domain.usecases

import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import javax.inject.Inject

class GetFavoriteImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
     suspend operator fun invoke() = imageRepository.getFavoriteImages()
}