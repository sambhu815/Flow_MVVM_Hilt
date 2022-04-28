package com.swapnil.flow_mvvm_hilt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.swapnil.flow_mvvm_hilt.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    protected abstract val viewModel: VM

    //Todo - activity object
    private val mainActivity: MainActivity? by lazy {
        activity as? MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupVM()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Todo - Handle UI Part
    protected open fun setupUI() = Unit

    //Todo - handle ViewModel observers
    protected open fun setupVM() {
        viewModel.finishRequest.observe(viewLifecycleOwner) {
            mainActivity?.finish()
        }

        viewModel.toastMessage.observe(viewLifecycleOwner) {
            mainActivity?.reactOnToast(it)
        }

        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            it(findNavController())
        }
    }
}