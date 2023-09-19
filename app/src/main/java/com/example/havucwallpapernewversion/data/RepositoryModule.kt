package com.example.havucwallpapernewversion.data

import com.example.havucwallpapernewversion.features.account.data.repository.AccountRepository
import com.example.havucwallpapernewversion.features.account.data.repository.AccountRepositoryImpl
import com.example.havucwallpapernewversion.features.categories.data.repository.CategoryRepository
import com.example.havucwallpapernewversion.features.categories.data.repository.CategoryRepositoryImpl
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository

    @Binds
    abstract fun provideAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl,
    ): AccountRepository

    @Binds
    abstract fun provideCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl,
    ): CategoryRepository
}