package com.example.joblogictodo

import android.app.Application
import com.datatheorem.android.trustkit.TrustKit
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TrustKit.initializeWithNetworkSecurityConfiguration(this, R.xml.network_security_config)
    }
}