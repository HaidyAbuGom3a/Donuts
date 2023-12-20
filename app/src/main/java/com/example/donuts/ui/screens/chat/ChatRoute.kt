package com.example.donuts.ui.screens.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "Chat"

fun NavController.navigateToChat() {
    navigate(ROUTE)
}

fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable(ROUTE) { ChatScreen(navController = navController) }
}