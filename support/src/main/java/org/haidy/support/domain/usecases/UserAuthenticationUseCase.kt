package org.haidy.support.domain.usecases

import org.haidy.support.domain.repository.IAuthRepository
import javax.inject.Inject

interface IUserAuthenticationUseCase {
    suspend fun signUpUser(username: String, email: String, password: String): Boolean
    suspend fun loginUser(email: String, password: String): String
}

class UserAuthenticationUseCase @Inject constructor(private val authRepository: IAuthRepository) :
    IUserAuthenticationUseCase {
    override suspend fun signUpUser(username: String, email: String, password: String): Boolean {
        return authRepository.signUpUser(username, email, password)
    }

    override suspend fun loginUser(email: String, password: String): String {
        return authRepository.loginUser(email, password)
    }

}