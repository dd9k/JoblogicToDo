package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.ProductSource
import kotlinx.coroutines.flow.Flow

interface ProductRemote {
    fun getAll(): Flow<List<ProductSource>>
}