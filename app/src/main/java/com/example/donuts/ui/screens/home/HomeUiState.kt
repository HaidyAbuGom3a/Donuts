package com.example.donuts.ui.screens.home

import com.example.donuts.domain.entities.Donut
import kotlin.math.round

data class HomeUiState(
    val isLoading: Boolean = false,
    val donuts: List<DonutUiState> = emptyList(),
    val offers: List<DonutUiState> = emptyList()
)

data class DonutUiState(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val oldPrice: Double = 0.0,
    val price: Double = 0.0,
    val isFavorite: Boolean = false
)

fun Donut.toDonutUiState() = DonutUiState(
    id = id,
    name = name,
    description = description,
    oldPrice = round(price),
    price = round(price - (price * discountPercentage)),
    image = imageUrl
)
