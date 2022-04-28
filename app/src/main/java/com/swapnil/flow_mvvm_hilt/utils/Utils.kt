package com.swapnil.flow_mvvm_hilt.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.swapnil.flow_mvvm_hilt.R

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}

fun View.setOnClickAction(runnable: Runnable) {
    setOnClickListener { runnable.run() }
}

fun View.showShortSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun Activity.showShortSnackBar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.glideLoad(url: String) {
    Glide.with(this.context).load(url).into(this)
}

fun Any.debug(message: String) {
    Log.d(this::class.java.simpleName, message)
}

fun Any.debug(message: String, t: Throwable) {
    Log.d(this::class.java.simpleName, message, t)
}

fun Any.error(message: String) {
    Log.e(this::class.java.simpleName, message)
}

fun Any.error(message: String, t: Throwable) {
    Log.e(this::class.java.simpleName, message, t)
}

fun Context.showAlertDialog(
    positiveButton: String, negativeButton: String, title: String, message: String,
    actionOnPositiveButton: () -> Unit, actionOnNegativeButton: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(positiveButton) { dialog, _ ->
            dialog.cancel()
            actionOnPositiveButton()
        }
        .setNegativeButton(negativeButton) { dialog, _ ->
            dialog.cancel()
            actionOnNegativeButton()
        }
    val alert = builder.create()
    alert.show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

