package com.swapnil.flow_mvvm_hilt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swapnil.flow_mvvm_hilt.databinding.ItemStoreBinding
import com.swapnil.flow_mvvm_hilt.model.ProductsItem
import com.swapnil.flow_mvvm_hilt.utils.glideLoad

class StoreDiffUtilsAdapter(
    private val onClickListener: (ProductsItem) -> Unit
) : ListAdapter<ProductsItem, StoreDiffUtilsAdapter.ViewHolder>(DiffCallBack()) {
   // lateinit var context: Context

    inner  class ViewHolder(var binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallBack : DiffUtil.ItemCallback<ProductsItem>() {
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // context = parent.context
        return ViewHolder(ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(getItem(position)){
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
}