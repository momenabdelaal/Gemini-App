package com.mobly.app.core.di.error

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorTypeHandlerImpl @Inject constructor() : ErrorTypeHandler {
    override fun getError(exception: Exception): ErrorType {
        return when (exception) {
            is UnknownHostException -> {
                ErrorType.NetworkError
            }
            is SocketTimeoutException ->{
                ErrorType.UnknownError
            }
            else -> {
                ErrorType.UnknownError
            }
        }
    }
}