package com.example.donuts.ui.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "Profile"

fun NavController.navigateToProfile() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(ROUTE) { ProfileScreen(navController = navController) }
}