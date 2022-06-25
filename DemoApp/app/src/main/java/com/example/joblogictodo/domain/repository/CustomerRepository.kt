package com.example.joblogictodo.domain.repository

import com.example.joblogictodo.domain.models.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    // Remote and cache
    fun getAll(isUseLocal: Boolean): Flow<List<Customer>>

    // Local
    suspend fun insert(listCustomer: List<Customer>)
}