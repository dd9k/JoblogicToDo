package com.example.joblogictodo.remote.mapper

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.remote.models.CustomerResponse
import javax.inject.Inject

class CustomerRemoteMapper @Inject constructor() : RemoteMapper<CustomerResponse, CustomerSource> {
    override fun mapFromResponse(type: CustomerResponse): CustomerSource {
        return CustomerSource(type.id, type.name, type.number)
    }
}