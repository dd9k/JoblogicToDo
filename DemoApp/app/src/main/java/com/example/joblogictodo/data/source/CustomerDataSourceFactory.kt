package com.example.joblogictodo.data.source

import com.example.joblogictodo.data.repository.CustomerDataSource
import javax.inject.Inject

class CustomerDataSourceFactory @Inject constructor(
    private val localDataSource: CustomerLocalDataSource,
    private val remoteDataSource: CustomerRemoteDataSource
) {

    open suspend fun getDataStore(isUseLocal: Boolean): CustomerDataSource {
        return if (isUseLocal) {
            return getLocalDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): CustomerDataSource {
        return remoteDataSource
    }

    fun getLocalDataSource(): CustomerDataSource {
        return localDataSource
    }
}