package org.haidy.support.domain.repository

import org.haidy.support.domain.entities.Support


interface IUserRepository {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun saveIfLoggedIn(isLogged: Boolean)
    suspend fun getIfLoggedIn(): Boolean
    suspend fun getUserData(): Support
}