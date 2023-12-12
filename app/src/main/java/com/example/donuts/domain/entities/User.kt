package com.example.donuts.domain.entities

data class User(
    val id: String,
    val username: String,
    val email: String,
    val imageUrl: String,
    val address: String
)
