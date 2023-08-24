package com.example.havucwallpapernewversion.data

import com.example.havucwallpapernewversion.features.account.data.repository.AccountRepository
import com.example.havucwallpapernewversion.features.account.data.repository.AccountRepositoryImpl
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepositoryImpl
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}