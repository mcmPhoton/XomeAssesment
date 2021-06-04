package com.sample.xomeassesment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class Networkutil {

    var isNetworkAvailableOverride: Boolean? = null

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        return isNetworkAvailableOverride?.let {
            isNetworkAvailableOverride
        } ?: run {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            cm.activeNetwork?.let { n ->
                cm.getNetworkCapabilities(n)?.let { nc ->
                    return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ) || nc.hasTransport(NetworkCapabilities.TRANSPORT_VPN) // TODO: Remove once dlx env available without Zcalar proxy
                }
            }

            return false
        }
    }
}
