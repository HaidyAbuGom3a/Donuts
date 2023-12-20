package org.haidy.support.data.mapper

import org.haidy.support.data.dto.SupportDto
import org.haidy.support.domain.entities.Support

fun SupportDto.toEntity(): Support {
    return Support(
        id = id ?: "",
        username = username ?: "",
        email = email ?: "",
        imageUrl = imageUrl ?: "",
    )
}