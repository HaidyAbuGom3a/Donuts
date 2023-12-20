package org.haidy.support.domain.repository

interface IAuthRepository {
    suspend fun signUpUser(username: String, email: String, password: String): Boolean
    suspend fun loginUser(email: String, password: String): String

}