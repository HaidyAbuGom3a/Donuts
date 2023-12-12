package com.example.donuts.data.mapper

import com.example.donuts.data.dto.UserDto
import com.example.donuts.domain.entities.User

fun UserDto.toEntity(): User {
    return User(
        id = id ?: "",
        username = username ?: "",
        email = email ?: "",
        imageUrl = imageUrl ?: "",
        address = address ?: ""
    )
}