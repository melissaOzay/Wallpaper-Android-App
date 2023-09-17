package com.example.havucwallpapernewversion.data.local.db.category.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryEntity>

}