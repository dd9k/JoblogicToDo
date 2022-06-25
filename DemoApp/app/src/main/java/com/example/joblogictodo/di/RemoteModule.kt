package com.example.joblogictodo.di

import com.datatheorem.android.trustkit.BuildConfig
import com.datatheorem.android.trustkit.TrustKit
import com.example.joblogictodo.Constants
import com.example.joblogictodo.data.repository.CustomerRemote
import com.example.joblogictodo.data.repository.ProductRemote
import com.example.joblogictodo.presentation.utils.PresentationPreferencesHelper
import com.example.joblogictodo.remote.networkService.BinRequestService
import com.example.joblogictodo.remote.networkService.ConnectivityInterceptor
import com.example.joblogictodo.remote.networkService.UnsafeOkHttpClient
import com.example.joblogictodo.remote.repository.CustomerRemoteImp
import com.example.joblogictodo.remote.repository.ProductRemoteImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor, connectivity: ConnectivityInterceptor, preferencesHelper: PresentationPreferencesHelper): OkHttpClient {
        val url = URL(preferencesHelper.serverUrl ?: Constants.SERVER_URL_DEFAULT)
        val host: String = url.host
        val sslSocketFactory: SSLSocketFactory = TrustKit.getInstance().getSSLSocketFactory(host)
        val trustManager: X509TrustManager = TrustKit.getInstance().getTrustManager(host)

        val client = UnsafeOkHttpClient.getUnsafeOkHttpClient(url)
            .followSslRedirects(true).connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(connectivity)
            .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
            .sslSocketFactory(sslSocketFactory, trustManager)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, preferencesHelper: PresentationPreferencesHelper): Retrofit {
        return Retrofit.Builder()
            .baseUrl(preferencesHelper.serverUrl ?: Constants.SERVER_URL_DEFAULT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideBinRequestService(retrofit: Retrofit): BinRequestService {
        return retrofit.create(BinRequestService::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomerRemote(customerRemote: CustomerRemoteImp): CustomerRemote {
        return customerRemote
    }

    @Provides
    @Singleton
    fun provideProductRemote(productRemote: ProductRemoteImp): ProductRemote {
        return productRemote
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppImpModule {
    @Singleton
    @Binds
    abstract fun bindsConnectivityInterceptor(interceptor: ConnectivityInterceptor): Interceptor

    @Singleton
    @Binds
    abstract fun bindsNoConnectivityException(interceptor: ConnectivityInterceptor.NoConnectivityException): IOException
}
