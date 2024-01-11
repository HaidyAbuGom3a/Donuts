package com.example.donuts.ui.screens.favorite

import com.example.donuts.domain.entities.Donut

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val items: List<FavoriteItemUiState> = emptyList()
)

data class FavoriteItemUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = ""
)

fun Donut.toFavoriteItemUiState(): FavoriteItemUiState{
    return FavoriteItemUiState(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun List<Donut>.toFavoriteUiState(): FavoriteUiState{
    return FavoriteUiState(
        items = this.map { it.toFavoriteItemUiState() }
    )
}