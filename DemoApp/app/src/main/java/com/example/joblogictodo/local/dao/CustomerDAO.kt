package com.example.joblogictodo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.joblogictodo.domain.models.Customer
import com.example.joblogictodo.local.models.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDAO {
    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME} WHERE id = :id")
    fun getById(id: Int): LiveData<CustomerEntity?>

    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME}")
    fun getAll(): Flow<List<CustomerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(list: List<CustomerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: CustomerEntity)

    @Query("DELETE FROM ${CustomerEntity.TABLE_NAME}")
    suspend fun deleteAll()
}