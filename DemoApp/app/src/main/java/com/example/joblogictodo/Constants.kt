package com.example.joblogictodo

object Constants {
    const val SERVER_URL_DEFAULT = "https://my-json-server.typicode.com/"
    const val TYPE_APP_JSON = "content-type: application/json"
    const val ERROR_CODE_UNKNOWN = "-99"
    const val ERROR_CODE_NAME_NOT_RESOLVE = "-100"
    const val ERROR_CODE_SOCKET_TIMEOUT = "-101"
    const val ERROR_CODE_PARSE_ERROR = "-102"
    const val ERROR_SSL = "-103"
    const val ERROR_MESSAGE_WRONG_DATA = "404"
    const val ERROR_NO_DATA = "-105"
    val LIST_NONE_MESSAGE = listOf(
        ERROR_CODE_UNKNOWN,
        ERROR_CODE_NAME_NOT_RESOLVE,
        ERROR_CODE_PARSE_ERROR,
        ERROR_SSL,
        ERROR_MESSAGE_WRONG_DATA
    )

    const val PATH_GET_CUSTOMER = "imkhan334/demo-1/call"
    const val PATH_GET_PRODUCT = "imkhan334/demo-1/buy"
    const val ACC_REFRESHTOKEN_EXPIRED = "ACC_ExpiredRefreshToken"
    const val WRONG_SOMETHING = "Wrong_Something"
}