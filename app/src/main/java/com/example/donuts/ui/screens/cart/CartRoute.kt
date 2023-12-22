package com.example.donuts.ui.screens.cart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.donuts.ui.navigation.Destination

private const val ROUTE = Destination.CART

fun NavController.navigateToCart(){
    navigate(ROUTE)
}
fun NavGraphBuilder.cartRoute(navController: NavController){
    composable(ROUTE){ CartScreen(navController = navController)}
}