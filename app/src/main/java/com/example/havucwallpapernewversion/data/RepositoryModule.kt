package com.example.havucwallpapernewversion.data

import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepository
import com.example.havucwallpapernewversion.features.images.data.repository.ImageRepositoryImpl
import com.example.havucwallpapernewversion.features.images.data.api.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserInfoRepository(apiService: ImageService,categoryImageService: ImageService,memoryService: ImageService): ImageRepository {
        return ImageRepositoryImpl(apiService,categoryImageService,memoryService)
    }


}