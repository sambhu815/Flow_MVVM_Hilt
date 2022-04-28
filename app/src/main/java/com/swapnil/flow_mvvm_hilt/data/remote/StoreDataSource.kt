package com.swapnil.flow_mvvm_hilt.data.remote

import javax.inject.Inject

class StoreDataSource @Inject constructor(
    private val storeServices: StoreServices
) {

    suspend fun getStore() = storeServices.getStore()
}