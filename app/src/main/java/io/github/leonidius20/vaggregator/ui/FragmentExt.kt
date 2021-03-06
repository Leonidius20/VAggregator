package io.github.leonidius20.vaggregator.ui

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() = ViewCompat.getWindowInsetsController(requireView())
    ?.hide(WindowInsetsCompat.Type.ime())

fun Fragment.isNetworkConnected(): Boolean {
    val cm = ContextCompat.getSystemService(context!!, ConnectivityManager::class.java)
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}