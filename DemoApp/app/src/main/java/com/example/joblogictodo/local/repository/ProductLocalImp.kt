package com.example.joblogictodo.local.repository

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.data.repository.ProductLocal
import com.example.joblogictodo.local.dao.ProductDAO
import com.example.joblogictodo.local.mapper.ProductLocalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductLocalImp @Inject constructor(private val productDAO: ProductDAO,
                                          private val mapper: ProductLocalMapper,) : ProductLocal {
    override fun getAll(): Flow<List<ProductSource>> = flow {
        productDAO.getAll().map { listProductEntity ->
            listProductEntity.map { productEntity -> mapper.mapFromEntity(productEntity) }
        }.collect {
            emit(it)
        }
    }

    override suspend fun insert(listProduct: List<ProductSource>) {
        productDAO.insertAll(
            listProduct.map {
                mapper.mapToEntity(it)
            }
        )
    }
}