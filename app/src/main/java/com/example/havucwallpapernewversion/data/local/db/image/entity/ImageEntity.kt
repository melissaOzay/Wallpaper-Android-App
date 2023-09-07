package com.example.havucwallpapernewversion.data.local.db.image.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "imageId")
    val imageId: String,
    @ColumnInfo(name = "imagePath")
    val imagePath: String,
    @ColumnInfo(name = "imageFullPath")
    val imageFullPath: String,

)