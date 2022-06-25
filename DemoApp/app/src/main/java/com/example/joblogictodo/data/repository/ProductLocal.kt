package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.ProductSource
import kotlinx.coroutines.flow.Flow

interface ProductLocal {
    fun getAll(): Flow<List<ProductSource>>
    suspend fun insert(listCharacters: List<ProductSource>)
}