package com.example.donuts.ui.screens.favorite

sealed class FavoriteUiEffect{
    data object NavigateUp: FavoriteUiEffect()
    data class NavigateToItem(val id: String): FavoriteUiEffect()
}
