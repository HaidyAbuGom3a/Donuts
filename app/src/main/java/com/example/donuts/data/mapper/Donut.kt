package com.example.donuts.data.mapper

import com.example.donuts.data.dto.DonutDto
import com.example.donuts.domain.entities.Donut

fun DonutDto.toEntity(): Donut{
    return Donut(
        id = id ?: "",
        name = name ?: "",
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        price = price ?: 0.0,
        hasOffer = hasOffer ?: false,
        discountPercentage = discountPercentage ?: 0.0
    )
}