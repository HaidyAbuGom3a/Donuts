package com.example.donuts.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "Details"

fun NavController.navigateToDetails(id: Int) {
    navigate("$ROUTE/$id")
}

fun NavGraphBuilder.detailsRoute(navController: NavController) {
    composable(
        "$ROUTE/{${DetailsArgs.ID_ARG}}",
        arguments = listOf(navArgument(DetailsArgs.ID_ARG) { NavType.IntType })
    ) { DetailsScreen(navController) }
}

class DetailsArgs(savedStateHandle: SavedStateHandle) {
    val id: String = savedStateHandle[ID_ARG]!!
    companion object {
        const val ID_ARG = "id"
    }
}
