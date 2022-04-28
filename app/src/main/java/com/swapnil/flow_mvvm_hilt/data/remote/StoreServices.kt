package com.swapnil.flow_mvvm_hilt.data.remote

import com.swapnil.flow_mvvm_hilt.model.StoreResponse
import com.swapnil.flow_mvvm_hilt.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface StoreServices {

    @GET(Constant.FETECH_DATA)
    suspend fun getStore(): Response<StoreResponse>
}