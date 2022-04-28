package com.swapnil.flow_mvvm_hilt.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("desc")
    val desc: String? = null
):Parcelable

