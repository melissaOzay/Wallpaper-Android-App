package com.example.havucwallpapernewversion.features.images.domain

import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(page: Int) = imageRepository.getImages(page)
}