package com.example.havucwallpapernewversion.features.categories.domain.usecase

import com.example.havucwallpapernewversion.features.categories.data.repository.CategoryRepository
import javax.inject.Inject

class GetImagesCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke() = categoryRepository.getCategories()
}