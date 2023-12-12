package com.example.donuts.ui.screens.details

import androidx.annotation.DrawableRes
import com.example.donuts.R
import com.example.donuts.domain.entities.Donut
import kotlin.math.round

data class DetailsUiState(
    val donutName: String = "",
    val description: String = "",
    val image: String = "" ,
    val price: Double = 0.0,
    val quantity: Int = 1,
    val isFavorite: Boolean = false
)

fun Donut.toDetailsUiState() = DetailsUiState(
    donutName = name,
    description = description,
    image = imageUrl,
    price = round(price - (price * discountPercentage)),
)