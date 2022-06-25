package com.example.joblogictodo.remote.repository

import com.example.joblogictodo.data.models.CustomerSource
import com.example.joblogictodo.data.repository.CustomerRemote
import com.example.joblogictodo.remote.mapper.CustomerRemoteMapper
import com.example.joblogictodo.remote.models.CustomerResponse
import com.example.joblogictodo.remote.networkService.BinService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomerRemoteImp @Inject constructor(private val binService: BinService,
                                            private val mapper: CustomerRemoteMapper,) : CustomerRemote {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAll(): Flow<List<CustomerSource>> = callbackFlow   {
        binService.requestGetCustomer(object : BinService.BaseCallBack<List<CustomerResponse>>{
            override fun onSuccess(record: List<CustomerResponse>) {
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