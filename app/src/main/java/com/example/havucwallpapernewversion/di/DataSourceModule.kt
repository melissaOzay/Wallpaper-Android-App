package com.example.havucwallpapernewversion.di

import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDS
import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDSImpl
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDS
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDSImp
import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDS
import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDSImpl
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDS
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDSImpl
import com.example.havucwallpapernewversion.features.images.data.local.ImageLocalDS
import com.example.havucwallpapernewversion.features.images.data.local.ImageLocalDSImpl
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDS
import com.example.havucwallpapernewversion.features.images.data.remote.ImageRemoteDSImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideRemoteImageDS(
        imageRemoteDS: ImageRemoteDSImpl,
    ): ImageRemoteDS

    @Binds
    abstract fun provideRemoteAccountDS(
        accountRemoteDS: AccountRemoteDSImp,
    ): AccountRemoteDS

    @Binds
    abstract fun provideLocalAccountDS(
        accountLocalDSImpl: AccountLocalDSImpl,
    ): AccountLocalDS

    @Binds
    abstract fun provideImageLocalDS(
        imageLocalDSImpl: ImageLocalDSImpl,
    ): ImageLocalDS

    @Binds
    abstract fun provideCategoryRemoteDS(
        categoryRemoteDSImpl: CategoryRemoteDSImpl,
    ): CategoryRemoteDS

    @Binds
    abstract fun provideCategoryLocalDS(
        categoryLocalDSImpl: CategoryLocalDSImpl,
    ): CategoryLocalDS
}