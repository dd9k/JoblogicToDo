package com.example.joblogictodo.data

import com.example.joblogictodo.data.mapper.ProductMapper
import com.example.joblogictodo.data.source.ProductDataSourceFactory
import com.example.joblogictodo.domain.models.Product
import com.example.joblogictodo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(private val dataSourceFactory: ProductDataSourceFactory,
                                               private val mapper: ProductMapper,) : ProductRepository {

    override fun getAll(isUseLocal: Boolean): Flow<List<Product>> = flow {
        dataSourceFactory.getDataStore(isUseLocal).getAll().map { listProductSource ->
            listProductSource.map { productSource ->
                mapper.mapFromSource(productSource)
            }
        }.collect {
            emit(it)
        }
    }

    override suspend fun insert(listProduct: List<Product>) {
        val productSource = listProduct.map { product ->
            mapper.mapToSource(product)
        }
        dataSourceFactory.getLocalDataSource().insert(productSource)
    }
}