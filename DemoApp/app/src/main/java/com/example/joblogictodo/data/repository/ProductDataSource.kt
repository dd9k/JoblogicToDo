package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.ProductSource
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    // Remote and local
    fun getAll(): Flow<List<ProductSource>>

    // Local
    suspend fun insert(listProduct: List<ProductSource>)
}