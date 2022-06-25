package com.example.joblogictodo.local.repository

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.data.repository.CustomerLocal
import com.example.joblogictodo.local.dao.CustomerDAO
import com.example.joblogictodo.local.mapper.CustomerLocalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CustomerLocalImp @Inject constructor(private val customerDAO: CustomerDAO,
                                           private val mapper: CustomerLocalMapper,) : CustomerLocal {
    override fun getAll(): Flow<List<CustomerSource>> = flow {
        customerDAO.getAll().map { listCustomerEntity ->
            listCustomerEntity.map { customerEntity -> mapper.mapFromEntity(customerEntity) }
        }.collect {
            emit(it)
        }
    }

    override suspend fun insert(listCustomer: List<CustomerSource>) {
        customerDAO.insertAll(
            listCustomer.map {
                mapper.mapToEntity(it)
            }
        )
    }
}