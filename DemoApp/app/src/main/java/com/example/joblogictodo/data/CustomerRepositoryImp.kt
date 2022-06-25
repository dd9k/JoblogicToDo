package com.example.joblogictodo.data

import com.example.joblogictodo.data.mapper.CustomerMapper
import com.example.joblogictodo.data.source.CustomerDataSourceFactory
import com.example.joblogictodo.domain.models.Customer
import com.example.joblogictodo.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImp @Inject constructor(private val dataSourceFactory: CustomerDataSourceFactory,
                                                private val mapper: CustomerMapper,) : CustomerRepository {

    override fun getAll(isUseLocal: Boolean): Flow<List<Customer>> = flow {
        dataSourceFactory.getDataStore(isUseLocal).getAll().map { listCustomerSource ->
            listCustomerSource.map { customerSource ->
                mapper.mapFromSource(customerSource)
            }
        }.collect {
            emit(it)
        }
    }

    override suspend fun insert(listCustomer: List<Customer>) {
        val customerSource = listCustomer.map { customer ->
            mapper.mapToSource(customer)
        }
        dataSourceFactory.getLocalDataSource().insert(customerSource)
    }
}