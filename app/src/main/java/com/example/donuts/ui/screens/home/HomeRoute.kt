package com.example.donuts.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "Home"

fun NavController.navigateToHome() {
    navigate(ROUTE)
}

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(ROUTE) { HomeScreen(navController = navController) }
}