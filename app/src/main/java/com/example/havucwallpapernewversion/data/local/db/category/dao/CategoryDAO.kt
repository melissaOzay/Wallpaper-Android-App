package com.example.havucwallpapernewversion.data.local.db.category.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.havucwallpapernewversion.data.local.db.category.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

}