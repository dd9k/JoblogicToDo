package com.example.joblogictodo.remote.networkService

import android.app.Application
import okhttp3.Interceptor
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityInterceptor @Inject constructor(private val application: Application) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
        if (!CommonUtil.isNetworkAvailable(application)) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = "No connection"
    }
}