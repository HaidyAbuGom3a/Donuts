package com.example.donuts.domain.usecases

import com.example.donuts.domain.repository.IAuthRepository
import javax.inject.Inject

interface IUserAuthenticationUseCase {
    suspend fun signUpUser(username: String, email: String, password: String, address: String): Boolean
    suspend fun loginUser(email: String, password: String): String
}

class UserAuthenticationUseCase @Inject constructor(private val authRepository: IAuthRepository) :
    IUserAuthenticationUseCase {
    override suspend fun signUpUser(username: String, email: String, password: String, address: String): Boolean {
        return authRepository.signUpUser(username, email, password, address)
    }

    override suspend fun loginUser(email: String, password: String): String {
        return authRepository.loginUser(email, password)
    }

}