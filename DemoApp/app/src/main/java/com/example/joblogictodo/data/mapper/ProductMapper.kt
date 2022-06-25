package com.example.joblogictodo.data.mapper

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.domain.models.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : Mapper<ProductSource, Product> {
    override fun mapFromSource(type: ProductSource): Product {
        return Product(type.id, type.name, type.price, type.quantity, type.price)
    }

    override fun mapToSource(type: Product): ProductSource {
        return ProductSource(type.id, type.name, type.price, type.quantity, type.price)
    }
}