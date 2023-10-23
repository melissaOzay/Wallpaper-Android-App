package com.example.havucwallpapernewversion.features.categories.domain.usecase

import com.example.havucwallpapernewversion.features.categories.data.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
     fun invoke() = categoryRepository.getCategories()
}