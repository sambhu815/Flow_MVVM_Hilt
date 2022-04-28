package com.swapnil.flow_mvvm_hilt.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.swapnil.flow_mvvm_hilt.base.BaseFragment
import com.swapnil.flow_mvvm_hilt.databinding.ProductDetailFragmentBinding
import com.swapnil.flow_mvvm_hilt.utils.glideLoad
import com.swapnil.flow_mvvm_hilt.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<ProductDetailFragmentBinding, HomeViewModel>(
    ProductDetailFragmentBinding::inflate
) {

    override val viewModel: HomeViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun setupUI() {
        super.setupUI()

        binding.tvName.text = args.product.name
        binding.tvDes.text = args.product.desc
        binding.tvPrice.text = "$${args.product.price}"
        binding.ivProduct.glideLoad(args.product.image!!)

    }
}