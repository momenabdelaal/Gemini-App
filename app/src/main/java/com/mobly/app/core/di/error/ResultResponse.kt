package com.mobly.app.core.di.error

sealed class ResultResponse<T>(val data: T?, val errorType: ErrorType?) {
    class Success<T>(data: T) : ResultResponse<T>(data, null)
    class Loading<T>() : ResultResponse<T>(null, null)
    class Error<T>(errorType: ErrorType?) : ResultResponse<T>(null, errorType)
}