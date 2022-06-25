package com.example.joblogictodo.local.mapper

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.domain.models.Customer
import com.example.joblogictodo.local.models.CustomerEntity
import javax.inject.Inject

class CustomerLocalMapper @Inject constructor() : LocalMapper<CustomerEntity, CustomerSource> {
    override fun mapFromEntity(type: CustomerEntity): CustomerSource {
        return CustomerSource(type.id, type.name, type.number)
    }

    override fun mapToEntity(type: CustomerSource): CustomerEntity {
        return CustomerEntity(type.id, type.name, type.number)
    }
}