package com.example.joblogictodo.data.mapper

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.domain.models.Customer
import javax.inject.Inject

class CustomerMapper @Inject constructor() : Mapper<CustomerSource, Customer> {
    override fun mapFromSource(type: CustomerSource): Customer {
        return Customer(type.id, type.name, type.number)
    }

    override fun mapToSource(type: Customer): CustomerSource {
        return CustomerSource(type.id, type.name, type.number)
    }
}