package com.example.donuts.domain.repository

interface IAuthRepository {
    suspend fun signUpUser(username: String, email: String, password: String, address: String): Boolean
    suspend fun loginUser(email: String, password: String): String

}