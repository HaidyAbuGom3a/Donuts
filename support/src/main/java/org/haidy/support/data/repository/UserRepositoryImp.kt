package org.haidy.support.data.repository

import android.content.res.Resources.NotFoundException
import org.haidy.support.data.local.IDataStore
import org.haidy.support.data.mapper.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import org.haidy.support.data.dto.SupportDto
import org.haidy.support.domain.entities.Support
import org.haidy.support.domain.repository.IUserRepository
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

    override suspend fun getUserData(): Support {
        val id = getUserId()
        return try {
            firestore.collection(CUSTOMER_SERVICE)
                .document(id)
                .get()
                .await().toObject<SupportDto>()?.toEntity() ?: throw NotFoundException()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    companion object {
        const val CUSTOMER_SERVICE = "customer_service"
    }

}