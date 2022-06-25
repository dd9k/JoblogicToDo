package com.example.joblogictodo.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.joblogictodo.Constants
import javax.inject.Inject

open class PresentationPreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.aqube.ram.presentation.preferences"
        private const val PREF_KEY_IS_DONE_DUMMY = "is_dummy"
        private const val PREF_KEY_SERVER_URL = "server_url"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var isDoneDummy: Boolean
        get() = preferences.getBoolean(PREF_KEY_IS_DONE_DUMMY, false)
        set(isDoneDummy) = preferences.edit().putBoolean(PREF_KEY_IS_DONE_DUMMY, isDoneDummy).apply()

    var serverUrl: String?
        get() = preferences.getString(PREF_KEY_SERVER_URL, Constants.SERVER_URL_DEFAULT)
        set(serverUrl) = preferences.edit().putString(PREF_KEY_SERVER_URL, serverUrl).apply()
}