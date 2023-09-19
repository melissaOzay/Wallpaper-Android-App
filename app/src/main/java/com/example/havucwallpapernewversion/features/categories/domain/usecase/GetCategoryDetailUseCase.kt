package com.example.havucwallpapernewversion.features.categories.domain.usecase

import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import javax.inject.Inject

class GetCategoryDetailUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(page: Int, query: String) =
        imageRepository.getSearchImages(page, query)
}