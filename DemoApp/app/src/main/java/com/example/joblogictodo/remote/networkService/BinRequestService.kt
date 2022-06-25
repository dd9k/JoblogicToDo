package com.example.joblogictodo.remote.networkService

import com.example.joblogictodo.remote.models.CustomerResponse
import com.example.joblogictodo.remote.models.ProductResponse
import retrofit2.Call
import retrofit2.http.*
import com.example.joblogictodo.Constants

interface BinRequestService {
    @Headers(Constants.TYPE_APP_JSON)
    @GET(Constants.PATH_GET_CUSTOMER)
    fun requestGetCustomer(): Call<List<CustomerResponse>>

    @Headers(Constants.TYPE_APP_JSON)
    @GET(Constants.PATH_GET_PRODUCT)
    fun requestGetProduct(): Call<List<ProductResponse>>
}