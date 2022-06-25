package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.repository.ProductDataSource
import javax.inject.Inject

class ProductDataSourceFactory @Inject constructor(
    private val localDataSource: ProductLocalDataSource,
    private val remoteDataSource: ProductRemoteDataSource
) {

    open suspend fun getDataStore(isUseLocal: Boolean): ProductDataSource {
        return if (isUseLocal) {
            return getLocalDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): ProductDataSource {
        return remoteDataSource
    }

    fun getLocalDataSource(): ProductDataSource {
        return localDataSource
    }
}