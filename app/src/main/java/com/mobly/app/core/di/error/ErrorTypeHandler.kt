package com.mobly.app.core.di.error


interface ErrorTypeHandler {
    fun getError(exception: Exception): ErrorType
}