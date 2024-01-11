package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.User
import com.example.donuts.domain.repository.IUserRepository
import javax.inject.Inject

interface IManageUserUseCase{
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun saveIfLoggedIn(isLogged: Boolean)
    suspend fun getIfLoggedIn(): Boolean
    suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean)
    suspend fun getIfFirstTimeUseApp(): Boolean
    suspend fun getUserData(): User
}

class ManageUserUseCase @Inject constructor(private val repository: IUserRepository): IManageUserUseCase{
    override suspend fun saveUserId(userId: String) {
        repository.saveUserId(userId)
    }

    override suspend fun getUserId(): String {
        return repository.getUserId()
    }

    override suspend fun saveIfLoggedIn(isLogged: Boolean) {
        repository.saveIfLoggedIn(isLogged)
    }

    override suspend fun getIfLoggedIn(): Boolean {
        return repository.getIfLoggedIn()
    }

    override suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean) {
        repository.saveIfFirstTimeUseApp(isFirstTime)
    }

    override suspend fun getIfFirstTimeUseApp(): Boolean {
        return repository.getIfFirstTimeUseApp()
    }

    override suspend fun getUserData(): User {
        return repository.getUserData()
    }

}