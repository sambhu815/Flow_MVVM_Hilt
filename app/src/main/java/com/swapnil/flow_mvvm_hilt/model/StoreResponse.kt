package com.swapnil.flow_mvvm_hilt.model

import com.google.gson.annotations.SerializedName
import com.swapnil.flow_mvvm_hilt.BuildConfig

data class StoreResponse(

    @field:SerializedName("products")
    val products: MutableList<ProductsItem>? = null
)

