package org.haidy.support.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.haidy.support.data.dto.SupportDto
import org.haidy.support.domain.repository.IAuthRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) :
    IAuthRepository {
    override suspend fun signUpUser(
        username: String,
        email: String,
        password: String
    ): Boolean {
        return try {
            val request = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()
            addUser(id = request.user?.uid, username = username, email = email)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun loginUser(email: String, password: String): String {
        val request = firebaseAuth.signInWithEmailAndPassword(email, password).apply {
            await()
        }
        return request.result.user?.uid ?: ""
    }

    private suspend fun addUser(
        id: String? = null,
        imageUrl: String? = null,
        username: String,
        email: String,
    ): Boolean {
        val userDocumentRef = firebaseFirestore.collection(CUSTOMER_SERVICE).document(id.toString())
        val user = firebaseAuth.currentUser
        return userDocumentRef.set(
            SupportDto(
                id = user?.uid,
                username = username,
                email = email,
                imageUrl = imageUrl
            )
        ).apply {
            await()
        }.isSuccessful
    }

    companion object {
        const val CUSTOMER_SERVICE = "customer_service"
    }

}