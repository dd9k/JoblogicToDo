package com.example.joblogictodo.domain.repository

import com.example.joblogictodo.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    // Remote and cache
    fun getAll(isUseLocal: Boolean): Flow<List<Product>>

    // Local
    suspend fun insert(listProduct: List<Product>)
}