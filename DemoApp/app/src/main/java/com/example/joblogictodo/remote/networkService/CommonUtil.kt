@file:Suppress("DEPRECATION")

package com.example.joblogictodo.remote.networkService

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object CommonUtil {
    /**
     * @return Boolean
     * true: Have INTERNET
     * false: No INTERNET
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else return false
    }
}
