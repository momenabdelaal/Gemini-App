package com.mobly.app.domain.usecase

import com.mobly.app.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepo: UserRepository) {

    suspend fun userLogin(userName: String, pass: String) =
        userRepo.userLogin(userName, pass)

}