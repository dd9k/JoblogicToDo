package com.example.joblogictodo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.local.models.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {
    @Query("SELECT * FROM ${ProductEntity.TABLE_NAME} WHERE id = :id")
    fun getById(id: Int): Flow<ProductEntity?>

    @Query("SELECT * FROM ${ProductEntity.TABLE_NAME}")
    fun getAll(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(list: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Product: ProductEntity)

    @Query("DELETE FROM ${ProductEntity.TABLE_NAME}")
    suspend fun deleteAll()
}