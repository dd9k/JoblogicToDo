package com.example.joblogictodo.remote.networkService

import com.example.joblogictodo.Constants
import okhttp3.OkHttpClient
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object UnsafeOkHttpClient {
    fun getUnsafeOkHttpClient(url: URL): OkHttpClient.Builder {
        try {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.hostnameVerifier { hostname, session ->
                val hv = HttpsURLConnection.getDefaultHostnameVerifier()
                val host: String = url.getHost()
                hv.verify(host, session)
            }
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}