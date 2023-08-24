package com.example.havucwallpapernewversion.di

import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDS
import com.example.havucwallpapernewversion.features.account.data.local.AccountLocalDSImpl
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDS
import com.example.havucwallpapernewversion.features.account.data.remote.AccountRemoteDSImp
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
}