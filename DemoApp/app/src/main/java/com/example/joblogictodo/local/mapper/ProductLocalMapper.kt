package com.example.joblogictodo.local.mapper

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.local.models.ProductEntity
import javax.inject.Inject

class ProductLocalMapper @Inject constructor() : LocalMapper<ProductEntity, ProductSource> {
    override fun mapFromEntity(type: ProductEntity): ProductSource {
        return ProductSource(type.id, type.name, type.price, type.quantity, type.price)
    }

    override fun mapToEntity(type: ProductSource): ProductEntity {
        return ProductEntity(type.id, type.name, type.price, type.quantity, type.price)
    }
}