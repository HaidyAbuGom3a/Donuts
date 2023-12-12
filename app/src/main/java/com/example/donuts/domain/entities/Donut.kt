package com.example.donuts.domain.entities

data class Donut(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val discountPercentage: Double,
    val price: Double,
    val hasOffer: Boolean
)