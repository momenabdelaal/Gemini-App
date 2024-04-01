package com.mobly.app.data.dataSource.network_apis

import com.mobly.app.core.di.error.ErrorType
import com.mobly.app.core.di.error.ErrorTypeHandler
import com.mobly.app.core.di.error.ResultResponse
import retrofit2.Response
import javax.inject.Inject

class RequestApiCall @Inject constructor(
    private val errorTypeHandler: ErrorTypeHandler
) {
    suspend fun <T : Any> requestApiCall(requestApi: suspend () -> Response<T>): ResultResponse<T> {
        return try {
            val response = requestApi.invoke()
            parseApiResponse(response)
        } catch (exception: Exception) {
       ResultResponse.Error(errorTypeHandler.getError(exception))
        }
    }

    private fun <T> parseApiResponse(response: Response<T>): ResultResponse<T> {
        try {
            if (response.isSuccessful) {
                response.body()?.let { apiRes ->
                    return ResultResponse.Success(apiRes)
                }
            }
            return ResultResponse.Error(ErrorType.ServerError)
        } catch (e: Exception) {
            return ResultResponse.Error(errorTypeHandler.getError(e))
        }
    }
}