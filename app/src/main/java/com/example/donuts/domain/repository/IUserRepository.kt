package com.example.donuts.domain.repository

import com.example.donuts.domain.entities.User

interface IUserRepository {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun saveIfLoggedIn(isLogged: Boolean)
    suspend fun getIfLoggedIn(): Boolean
    suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean)
    suspend fun getIfFirstTimeUseApp(): Boolean
    suspend fun getUserData(): User
}