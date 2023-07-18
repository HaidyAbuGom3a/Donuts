package com.example.donuts.ui.screens.home

import androidx.annotation.DrawableRes
import com.example.donuts.domain.entities.DonutEntity

data class HomeUiState(
    val donuts: List<DonutUiState> = emptyList(),
    val offers: List<DonutUiState> = emptyList()
)

data class DonutUiState(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    @DrawableRes val image: Int = 0,
    val oldPrice:Int = 0,
    val newPrice:Int = 0,
    val isFavorite:Boolean = false
)

fun DonutEntity.toDonutUiState() = DonutUiState(
    id = id,
    name = name ?: "",
    description = description ?: "",
    oldPrice = oldPrice ?: 0,
    newPrice = newPrice ?: 0,
    image = image ?: 0
)
