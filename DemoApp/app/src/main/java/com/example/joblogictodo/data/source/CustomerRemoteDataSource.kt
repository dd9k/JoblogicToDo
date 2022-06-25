package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.data.repository.CustomerDataSource
import com.example.joblogictodo.data.repository.CustomerRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRemoteDataSource @Inject constructor(private val customerRemote: CustomerRemote) : CustomerDataSource {

    override fun getAll(): Flow<List<CustomerSource>> {
        return customerRemote.getAll()
    }

    override suspend fun insert(listCustomer: List<CustomerSource>) {

    }
}