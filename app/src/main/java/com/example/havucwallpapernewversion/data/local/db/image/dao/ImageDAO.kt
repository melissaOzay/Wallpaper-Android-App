package com.example.havucwallpapernewversion.data.local.db.image.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.havucwallpapernewversion.data.local.db.image.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageEntity: ImageEntity): Long

    @Query("DELETE FROM images WHERE imageId = :imageId")
    fun deleteImage(imageId: String)

    @Query("SELECT * FROM images")
    fun getImages(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM images")
    suspend fun getImagesSingle(): List<ImageEntity>

    @Query("SELECT EXISTS(SELECT * FROM images WHERE imageId = :imageId)")
    fun isImageFavori(imageId : String) : Boolean


}