package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.data.repository.ProductDataSource
import com.example.joblogictodo.data.repository.ProductLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(private val productLocal: ProductLocal) : ProductDataSource {

    override fun getAll(): Flow<List<ProductSource>> {
        return productLocal.getAll()
    }

    override suspend fun insert(listProduct: List<ProductSource>) {
        productLocal.insert(listProduct)
    }
}