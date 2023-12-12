package com.example.donuts.ui.screens.details

sealed class DetailsUiEffect{
    data object ShowAddToCartMessage: DetailsUiEffect()
    data object NavigateUp: DetailsUiEffect()
}
