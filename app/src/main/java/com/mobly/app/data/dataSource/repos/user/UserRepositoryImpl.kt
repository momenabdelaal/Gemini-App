package com.mobly.app.data.dataSource.repos.user

import com.mobly.app.core.di.error.ResultResponse
import com.mobly.app.domain.entity.LoginResponse
import com.mobly.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
//    private val userLocalSource: UserLocalDataSource,
    private val userRepository: UserRepository
) : UserRepository {


    override suspend fun userLogin(
        userName: String,
        pass: String
    ): ResultResponse<LoginResponse> =
        userRepository.userLogin(userName, pass)


}