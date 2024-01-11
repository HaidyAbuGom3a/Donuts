package com.example.donuts.data.dto


data class DonutDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val discountPercentage: Double? = null,
    val price: Double? = null,
    val hasOffer: Boolean? = null
)
