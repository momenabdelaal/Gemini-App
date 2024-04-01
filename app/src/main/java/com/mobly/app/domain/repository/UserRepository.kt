package com.mobly.app.domain.repository

import com.mobly.app.core.di.error.ResultResponse
import com.mobly.app.domain.entity.LoginResponse

interface UserRepository {

    suspend fun userLogin(userName: String, pass: String): ResultResponse<LoginResponse>

}