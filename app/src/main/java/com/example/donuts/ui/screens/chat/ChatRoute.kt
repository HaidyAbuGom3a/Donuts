package com.example.donuts.ui.screens.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.donuts.ui.navigation.Destination

private const val ROUTE = Destination.SUPPORT

fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable(ROUTE) { ChatScreen(navController = navController) }
}