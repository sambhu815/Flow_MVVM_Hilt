package com.swapnil.flow_mvvm_hilt.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.swapnil.flow_mvvm_hilt.R
import com.swapnil.flow_mvvm_hilt.utils.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    //viewModel request
    val finishRequest: LiveData<Unit> get() = _finishRequest
    private val _finishRequest = SingleLiveEvent<Unit>()

    val toastMessage: LiveData<String?> get() = mToastMessage
    private val mToastMessage = SingleLiveEvent<String?>()

    //navigation Graph
    private fun getNavOptions(
        optionalPopUpToId: Int? = null,
        inclusive: Boolean? = null
    ) = NavOptions.Builder().apply {
        setEnterAnim(R.anim.slide_in_right)
        setExitAnim(R.anim.slide_out_left)
        setPopEnterAnim(android.R.anim.slide_in_left)
        setPopExitAnim(android.R.anim.slide_out_right)
        optionalPopUpToId?.let {
            setPopUpTo(optionalPopUpToId, inclusive ?: false)
        }
    }.build()

    val navigationEvent: LiveData<NavController.() -> Any> get() = _navigationEvent
    private val _navigationEvent = SingleLiveEvent<NavController.() -> Any>()

    private fun navigateInDirection(directions: NavDirections) {
        _navigationEvent.postValue {
            navigate(directions, getNavOptions())
        }
    }

    protected fun NavController.safeNavigation(directions: NavDirections) =
        currentDestination?.getAction(directions.actionId)?.run {
            navigateInDirection(directions)
        }

    protected fun goBackUpTo(
        destinyId: Int, inclusive: Boolean
    ) = _navigationEvent.postValue {
        navigate(
            destinyId, null, getNavOptions(
                optionalPopUpToId = destinyId,
                inclusive = inclusive
            )
        )
    }

    protected fun navigationUp() {
        _navigationEvent.postValue { navigateUp() }
    }
}