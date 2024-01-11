package com.example.donuts.ui.screens.home

sealed class HomeUiEffect {
    data class NavigateToDonutDetails(val id: String): HomeUiEffect()
}