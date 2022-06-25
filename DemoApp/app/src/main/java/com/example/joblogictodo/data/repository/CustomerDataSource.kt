package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.CustomerSource
import kotlinx.coroutines.flow.Flow

interface CustomerDataSource {
    // Remote and local
    fun getAll(): Flow<List<CustomerSource>>

    // Local
    suspend fun insert(listCustomer: List<CustomerSource>)
}