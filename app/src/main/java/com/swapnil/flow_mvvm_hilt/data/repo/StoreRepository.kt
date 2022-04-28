package com.swapnil.flow_mvvm_hilt.data.repo

import com.swapnil.flow_mvvm_hilt.base.BaseApiResponse
import com.swapnil.flow_mvvm_hilt.data.remote.StoreDataSource
import com.swapnil.flow_mvvm_hilt.model.StoreResponse
import com.swapnil.flow_mvvm_hilt.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class StoreRepository @Inject constructor(
    private val storeDataSource: StoreDataSource
) : BaseApiResponse() {

    fun getStore(): Flow<NetworkResult<StoreResponse>> {
        return flow<NetworkResult<StoreResponse>> {
            emit(safeApiCall { storeDataSource.getStore() })
        }.flowOn(Dispatchers.IO)
    }
}