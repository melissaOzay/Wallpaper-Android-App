package com.example.havucwallpapernewversion.data

import com.example.havucwallpapernewversion.feature.data.api.ImageService
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
    fun provideUserInfoRepository(apiService: ImageService): TermRepository {
        return TermRepositoryImpl(apiService)
    }
}