package com.example.joblogictodo.remote.mapper

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.remote.models.ProductResponse
import javax.inject.Inject

class ProductRemoteMapper @Inject constructor() : RemoteMapper<ProductResponse, ProductSource> {
    override fun mapFromResponse(type: ProductResponse): ProductSource {
        return ProductSource(type.id, type.name, type.price, type.quantity, type.price)
    }
}