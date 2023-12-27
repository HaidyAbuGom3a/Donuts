package org.haidy.support.ui.screens.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.support.ui.navigation.SupportDestination

private const val ROUTE = SupportDestination.CHAT

fun NavController.navigateToChat() {
    popBackStack()
    navigate(ROUTE)
}

fun NavGraphBuilder.chatRoute() {
    composable(ROUTE) { ChatScreen() }
}