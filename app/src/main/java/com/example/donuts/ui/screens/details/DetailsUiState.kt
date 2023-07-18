package com.example.donuts.ui.screens.details

import androidx.annotation.DrawableRes
import com.example.donuts.domain.entities.DonutEntity

data class DetailsUiState(
    val donutName: String = "",
    val description: String = "",
    @DrawableRes val image: Int = 0,
    val price: Int = 0
)

fun DonutEntity.toDetailsUiState() = DetailsUiState(
    donutName = name ?: "",
    description = description ?: "",
    image = image ?: 0,
    price = newPrice ?: 0,
    )