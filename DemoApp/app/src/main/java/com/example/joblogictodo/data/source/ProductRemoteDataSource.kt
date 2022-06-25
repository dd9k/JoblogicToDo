package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.data.repository.ProductDataSource
import com.example.joblogictodo.data.repository.ProductRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val productRemote: ProductRemote) : ProductDataSource {

    override fun getAll(): Flow<List<ProductSource>> {
        return productRemote.getAll()
    }

    override suspend fun insert(listProduct: List<ProductSource>) {

    }
}