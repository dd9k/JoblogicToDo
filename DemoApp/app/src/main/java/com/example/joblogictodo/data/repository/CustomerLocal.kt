package com.example.joblogictodo.data.repository

import com.example.joblogictodo.data.models.CustomerSource
import kotlinx.coroutines.flow.Flow

interface CustomerLocal {
    fun getAll(): Flow<List<CustomerSource>>
    suspend fun insert(listCustomer: List<CustomerSource>)
}