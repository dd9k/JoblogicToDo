package com.example.joblogictodo.remote.repository

import com.example.joblogictodo.data.models.ProductSource
import com.example.joblogictodo.data.repository.ProductRemote
import com.example.joblogictodo.remote.mapper.ProductRemoteMapper
import com.example.joblogictodo.remote.models.ProductResponse
import com.example.joblogictodo.remote.networkService.BinService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ProductRemoteImp @Inject constructor(private val binService: BinService,
                                           private val mapper: ProductRemoteMapper,) : ProductRemote {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAll(): Flow<List<ProductSource>> = callbackFlow  {
        binService.requestGetProduct(object : BinService.BaseCallBack<List<ProductResponse>>{
            override fun onSuccess(record: List<ProductResponse>) {
                offer(record.map {
                    mapper.mapFromResponse(it)
                })
            }

            override fun onError(exception: BinService.BinServiceException) {
                close(exception)
            }
        })
        awaitClose { channel.close() }
    }

}