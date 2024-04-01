package com.mobly.app.data.dataSource.user.remote

import com.mobly.app.data.dataSource.network_apis.RequestApiCall
import com.mobly.app.data.dataSource.network_apis.ServicesAPIs
import com.mobly.app.domain.entity.LoginResponse
import com.mobly.app.domain.repository.UserRepository

import com.mobly.app.core.di.error.ResultResponse
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val servicesAPIs: ServicesAPIs,
    private val requestApiCall: RequestApiCall,
) : UserRepository {


    override suspend fun userLogin(
        userName: String,
        pass: String
    ): ResultResponse<LoginResponse> {
        val res = requestApiCall.requestApiCall {
            servicesAPIs.userLogin(
                1,
                userName = userName,
                password = pass
            )
        }

        return if (res is ResultResponse.Success && res.data != null) {
            ResultResponse.Success(res.data)
        } else {
            ResultResponse.Error(res.errorType)
        }
    }



}