package com.example.donuts.data.repository

import com.example.donuts.data.dto.UserDto
import com.example.donuts.domain.repository.IAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
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
        password: String,
        address: String
    ): Boolean {
        return try {
            val request = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()
            addUser(id = request.user?.uid, username = username, email = email, address = address)
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
        address: String
    ): Boolean {
        val userDocumentRef = firebaseFirestore.collection(USERS).document(id.toString())
        val user = firebaseAuth.currentUser
        return userDocumentRef.set(
            UserDto(
                id = user?.uid,
                username = username,
                email = email,
                imageUrl = imageUrl,
                address = address
            )
        ).apply {
            await()
        }.isSuccessful
    }

    companion object {
        const val USERS = "users"
    }

}