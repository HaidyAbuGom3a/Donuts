package com.example.donuts.data.local

interface IDataStore {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun saveIfLoggedIn(isLogged: Boolean)
    suspend fun getIfLoggedIn(): Boolean
    suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean)
    suspend fun getIfFirstTimeUseApp(): Boolean
}