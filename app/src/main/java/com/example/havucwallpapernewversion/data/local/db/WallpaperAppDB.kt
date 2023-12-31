package com.example.havucwallpapernewversion.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.havucwallpapernewversion.data.local.db.category.dao.CategoryDAO
import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import com.example.havucwallpapernewversion.data.local.db.image.dao.ImageDAO
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity

@Database(
    entities = [
        ImageEntity::class,
        CategoryEntity::class
    ],
    version =1
)
abstract class WallpaperAppDB : RoomDatabase() {
    abstract fun getImageDAO(): ImageDAO
    abstract fun getCategoryDAO(): CategoryDAO
}