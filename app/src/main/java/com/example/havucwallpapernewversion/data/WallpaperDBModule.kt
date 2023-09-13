package com.example.havucwallpapernewversion.data

import android.content.Context
import androidx.room.Room
import com.example.havucwallpapernewversion.data.local.db.WallpaperAppDB
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperDBModule {

    @Singleton
    @Provides
    fun provideWallpaperDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app,
        WallpaperAppDB::class.java,
        "wallpaper_db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries()
        .build()

    @Reusable
    @Provides
    fun provideImageDAO(db: WallpaperAppDB) = db.getImageDAO()

}