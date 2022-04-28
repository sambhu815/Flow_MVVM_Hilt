package com.swapnil.flow_mvvm_hilt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swapnil.flow_mvvm_hilt.databinding.ItemStoreBinding
import com.swapnil.flow_mvvm_hilt.model.ProductsItem
import com.swapnil.flow_mvvm_hilt.utils.glideLoad

class StoreAdapter(
    private val onClickListener: (ProductsItem) -> Unit
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    lateinit var context: Context
    private var storeList: MutableList<ProductsItem> = ArrayList()

    inner class ViewHolder(val binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(storeList[position]) {
                binding.tvName.text = name
                binding.tvDes.text = desc
                binding.tvPrice.text = "$${price}"
                binding.ivProduct.glideLoad(image!!)

                itemView.setOnClickListener {
                    onClickListener(this)
                }
            }
        }
    }

    fun setData(storeList: MutableList<ProductsItem>) {
        this.storeList = storeList
        notifyDataSetChanged()
    }

    override fun getItemCount() = storeList.size
}