package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.data.repository.CustomerDataSource
import com.example.joblogictodo.data.repository.CustomerLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerLocalDataSource @Inject constructor(private val customerLocal: CustomerLocal) : CustomerDataSource {

    override fun getAll(): Flow<List<CustomerSource>> {
        return customerLocal.getAll()
    }

    override suspend fun insert(listCustomer: List<CustomerSource>) {
        customerLocal.insert(listCustomer)
    }
}