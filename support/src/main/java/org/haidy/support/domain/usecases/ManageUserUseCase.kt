package org.haidy.support.domain.usecases

import org.haidy.support.domain.entities.Support
import org.haidy.support.domain.repository.IUserRepository
import javax.inject.Inject

interface IManageUserUseCase {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun saveIfLoggedIn(isLogged: Boolean)
    suspend fun getIfLoggedIn(): Boolean
    suspend fun getUserData(): Support
}

class ManageUserUseCase @Inject constructor(private val repository: IUserRepository) :
    IManageUserUseCase {
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

    override suspend fun getUserData(): Support {
        return repository.getUserData()
    }

}