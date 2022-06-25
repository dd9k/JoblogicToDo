package com.example.joblogictodo.remote.networkService

import android.app.Application
import android.content.Context
import android.util.Log
import android.util.MalformedJsonException
import com.datatheorem.android.trustkit.BuildConfig
import com.example.joblogictodo.R
import com.example.joblogictodo.remote.models.CustomerResponse
import com.example.joblogictodo.remote.models.ProductResponse
import com.example.joblogictodo.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.EOFException
import java.io.IOException
import java.net.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.*

@Singleton
class BinService @Inject constructor(private val service: BinRequestService, val application: Application) {

    fun requestGetCustomer(callback: BaseCallBack<List<CustomerResponse>>) {
        service.requestGetCustomer().enqueue(
            BinCallback(callback, application)
        )
    }

    fun requestGetProduct(callback: BaseCallBack<List<ProductResponse>>) {
        service.requestGetProduct().enqueue(
            BinCallback(callback, application)
        )
    }

    /**
     * Throwable to catch Service error
     */
    open class BinServiceException : Exception {
        var errorBody: ResponseBody? = null
        var errorCode: String? = null
        final override var message: String? = null
        var throwable: Throwable? = null
        private val TAG_EXCEPTION = "BinServiceException"

        constructor(
            throwable: Throwable, message: String?, errorCode: String) : super("Error when get response from Bin Service") {
            this.throwable = throwable
            this.message = message
            this.errorCode = errorCode
        }

        override fun toString(): String {
            return throwable?.toString() ?: super.toString()
        }

        fun getMessageError(): String{
            return message ?: "Error when get response from Bin Service"
        }

        fun getCodeError(): String{
            return errorCode ?: "Error when get response from Bin Service"
        }

        override fun printStackTrace() {
            throwable?.printStackTrace() ?: super.printStackTrace()
            try {
                Log.w(TAG_EXCEPTION, "Error response is: " + errorBody?.string())
            } catch (ex: Exception) {
                Log.w(TAG_EXCEPTION, ex)
            }
        }
    }

    interface BaseCallBack<T> {
        /**
         * Call after request success and response match data
         *
         * @param record  Return object in json response
         */
        fun onSuccess(record: T)
        /**
         * Call when application throw error
         *
         * @param exception
         */
        fun onError(exception: BinServiceException)
    }

    /**
     * Class provide pre execute data to response to callBack
     *
     * @param <U> Response data format
    </U> */
    class BinCallback<U>(private var callBack: BaseCallBack<U>, private val context: Context) : Callback<U> {
        private val TAG_EXCEPTION = "BinServiceException"

        override fun onResponse(call: Call<U>, response: Response<U>?) {
            if (response?.isSuccessful == true) {
                val body = response.body()
                if (body != null) {
                    callBack.onSuccess(body)
                } else {
                    onFailure(call, BinServiceException(Exception(), context.getString(R.string.no_data_found), Constants.ERROR_NO_DATA))
                }
            } else {
                val url = call.request().url().toString()
                try {
                    when (response?.code()) {
                        400, 404 -> {
                            handleException(Exception("Error " + response.code() + " bad request : " + url))
                            callBack.onError(BinServiceException(Exception(), "Error " + response.code() + " bad request",
                                Constants.ERROR_MESSAGE_WRONG_DATA))
                        }
                        else -> {
                            handleException(Exception("Error 500 on url : $url"))
                            callBack.onError(BinServiceException(Exception(), "Server error : 500",
                                response?.code().toString()))
                        }
                    }
                } catch (e: Exception) {
                    onFailure(call, e)
                }

                try {
                    val url = response?.raw()?.request()?.url()?.url()?.toString()
                    val code = response?.raw()?.networkResponse()?.code()
                    Log.w(TAG_EXCEPTION, "Failed to request: $url with error code: $code")

                } catch (ex: Exception) {
                    Log.w(TAG_EXCEPTION, ex)
                }
            }
        }


        override fun onFailure(call: Call<U>?, t: Throwable?) {
            parseBinException(context, t)?.let {
                handleException(it)
                callBack.onError(it)
            }
        }

        /**
         * Handle show error for exception
         *
         * @param exception
         */
        fun handleException(exception: Exception?) {
            val hashmap = HashMap<String, Any>()
            hashmap["exception_type"] = exception?.toString() ?: ""
            hashmap["message"] = exception?.message ?: ""
            exception?.printStackTrace()
        }
    }

    companion object {

        fun parseBinException(context: Context, t: Throwable?): BinServiceException? {
            val exception: BinServiceException?
            when (t) {
                is ConnectivityInterceptor.NoConnectivityException -> {
                    exception = BinServiceException(t, context.getString(R.string.internet_error), Constants.ERROR_CODE_SOCKET_TIMEOUT)
                }
                is UnknownHostException, is ConnectException -> exception =
                    BinServiceException(t, t.message, Constants.ERROR_CODE_NAME_NOT_RESOLVE)
                is SocketTimeoutException -> exception = BinServiceException(t, t.message, Constants.ERROR_CODE_SOCKET_TIMEOUT)
                is MalformedJsonException -> exception = BinServiceException(t, t.message, Constants.ERROR_CODE_PARSE_ERROR)
                is EOFException -> exception =
                    BinServiceException(t, t.message, Constants.ERROR_MESSAGE_WRONG_DATA)
                is BinServiceException -> {
                    exception = getMessageFromCode(context, t)
                }
                is SocketException -> exception = BinServiceException(t, t.message, Constants.ERROR_CODE_NAME_NOT_RESOLVE)
                is SSLHandshakeException,
                is CertificateException,
                is SSLPeerUnverifiedException -> exception = BinServiceException(t, t.message, Constants.ERROR_SSL)
                else -> {
                    exception = BinServiceException(t ?: Exception(), t?.message, Constants.ERROR_CODE_UNKNOWN)
                    if (BuildConfig.DEBUG) {
                        t?.printStackTrace()
                    }
                }
            }
            return exception
        }

        private fun getMessageFromCode(context: Context, t: BinServiceException): BinServiceException {
            return when(t.errorCode) {
                Constants.ACC_REFRESHTOKEN_EXPIRED -> {
                    BinServiceException(t, context.getString(R.string.acc_refreshtoken_expired),
                        Constants.ACC_REFRESHTOKEN_EXPIRED)
                }
                else -> {
                    BinServiceException(t, context.getString(R.string.something_wrong), Constants.WRONG_SOMETHING)
                }
            }
        }
    }
}