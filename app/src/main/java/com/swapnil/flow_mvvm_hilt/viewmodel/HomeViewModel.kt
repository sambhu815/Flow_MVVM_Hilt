package com.swapnil.flow_mvvm_hilt.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.swapnil.flow_mvvm_hilt.base.BaseViewModel
import com.swapnil.flow_mvvm_hilt.data.repo.StoreRepository
import com.swapnil.flow_mvvm_hilt.model.ProductsItem
import com.swapnil.flow_mvvm_hilt.model.StoreResponse
import com.swapnil.flow_mvvm_hilt.ui.HomeFragmentDirections
import com.swapnil.flow_mvvm_hilt.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    application: Application
) : BaseViewModel(application) {

    private val _storeResponse: MutableLiveData<NetworkResult<StoreResponse>> = MutableLiveData()
    val storeResponse: LiveData<NetworkResult<StoreResponse>> = _storeResponse

    fun fetchStore() = viewModelScope.launch {
        storeRepository.getStore().collect {
            _storeResponse.value = it
        }
    }

    fun onProductClick(findNavController: NavController, productsItem: ProductsItem,name: String) {
        findNavController.safeNavigation(HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
            product = productsItem, name = name
        ))
    }
}