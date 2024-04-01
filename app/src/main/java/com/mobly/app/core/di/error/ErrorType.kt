package com.mobly.app.core.di.error

sealed class ErrorType {
    data object NetworkError : ErrorType()
    data object ServerError : ErrorType()
    data object UnknownError : ErrorType()
    data object DataError : ErrorType()
}