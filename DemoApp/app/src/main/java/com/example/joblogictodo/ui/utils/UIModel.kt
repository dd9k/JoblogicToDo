package com.example.joblogictodo.ui.utils

import android.content.Context
import com.example.joblogictodo.Constants.LIST_NONE_MESSAGE
import com.example.joblogictodo.R
import com.example.joblogictodo.remote.networkService.BinService

class UIModel<T>(var data: T?,
                      var exception: BinService.BinServiceException?) {
    fun isSuccess(): Boolean = exception == null

    fun getErrorMessage(context: Context): String {
        if (exception?.errorCode == null || LIST_NONE_MESSAGE.contains(exception?.errorCode)) {
            return context.getString(R.string.something_wrong)
        }
        return exception?.message ?: context.getString(R.string.something_wrong)
    }

    companion object {
        fun <T> success(data: T): UIModel<T> {
            return UIModel(data, null)
        }

        fun <T> error(exception: BinService.BinServiceException): UIModel<T> {
            return UIModel(null, exception)
        }
    }
}