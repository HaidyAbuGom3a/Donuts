package com.example.donuts.data.repository

import android.content.res.Resources.NotFoundException
import com.example.donuts.data.dto.UserDto
import com.example.donuts.data.local.IDataStore
import com.example.donuts.domain.entities.User
import com.example.donuts.data.mapper.toEntity
import com.example.donuts.domain.repository.IUserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val datastore: IDataStore
) : IUserRepository {
    override suspend fun saveUserId(userId: String) {
        datastore.saveUserId(userId)
    }

    override suspend fun getUserId(): String {
        return datastore.getUserId()
    }

    override suspend fun saveIfLoggedIn(isLogged: Boolean) {
        datastore.saveIfLoggedIn(isLogged)
    }

    override suspend fun getIfLoggedIn(): Boolean {
        return datastore.getIfLoggedIn()
    }

    override suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean) {
        datastore.saveIfFirstTimeUseApp(isFirstTime)
    }

    override suspend fun getIfFirstTimeUseApp(): Boolean {
        return datastore.getIfFirstTimeUseApp()
    }

    override suspend fun getUserData(): User {
        val id = getUserId()
        return try {
            firestore.collection(USERS)
                .document(id)
                .get()
                .await().toObject<UserDto>()?.toEntity() ?: throw NotFoundException()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    companion object {
        const val USERS = "users"
    }

}