package com.example.donuts.ui.screens.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.donuts.ui.navigation.Destination

private const val ROUTE = Destination.FAVORITE

fun NavGraphBuilder.favoriteRoute(navController: NavController) {
    composable(ROUTE) { FavoriteScreen(navController = navController) }
}