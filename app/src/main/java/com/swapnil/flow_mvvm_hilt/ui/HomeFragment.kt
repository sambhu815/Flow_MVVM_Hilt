package com.swapnil.flow_mvvm_hilt.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapnil.flow_mvvm_hilt.base.BaseFragment
import com.swapnil.flow_mvvm_hilt.databinding.HomeFragmentBinding
import com.swapnil.flow_mvvm_hilt.ui.adapter.StoreAdapter
import com.swapnil.flow_mvvm_hilt.utils.NetworkResult
import com.swapnil.flow_mvvm_hilt.utils.gone
import com.swapnil.flow_mvvm_hilt.utils.visible
import com.swapnil.flow_mvvm_hilt.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(
    HomeFragmentBinding::inflate
) {

    override val viewModel: HomeViewModel by viewModels()

    lateinit var storeAdapter: StoreAdapter

    override fun setupUI() {
        super.setupUI()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        storeAdapter = StoreAdapter {
            viewModel.onProductClick(findNavController(), it, it.name!!)
        }

        binding.rvStore.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = null
            adapter = storeAdapter
        }
    }

    override fun setupVM() {
        super.setupVM()

        //fetchStoreData
        fetchStoreData()
    }

    private fun fetchStoreData() {
        fetchStore()
        viewModel.storeResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    storeAdapter.setData(it.data?.products!!)
                    binding.pdStore.gone()
                }
                is NetworkResult.Error -> {
                    binding.pdStore.gone()
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading ->
                    binding.pdStore.visible()
            }
        }
    }

    private fun fetchStore() {
        viewModel.fetchStore()
        binding.pdStore.visible()
    }

}