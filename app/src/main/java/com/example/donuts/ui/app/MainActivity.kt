package com.example.donuts.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.util.fastForEach
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.donuts.R
import com.example.donuts.ui.composables.DonutsBottomNavItem
import com.example.donuts.ui.composables.DonutsBottomNavigation
import com.example.donuts.ui.navigation.Destination
import com.example.donuts.ui.navigation.DonutsNavGraph
import com.example.donuts.ui.theme.DonutsTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalBottomNavPadding = compositionLocalOf<PaddingValues> {  error("No padding values found!") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    data class NavigationBarItem(
        val route: String,
        @DrawableRes val selectedIcon: Int,
        @DrawableRes val unselectedIcon: Int
    )

    private val items = listOf(
        NavigationBarItem(Destination.HOME, R.drawable.icon_home_filled, R.drawable.home_icon),
        NavigationBarItem(
            Destination.FAVORITE,
            R.drawable.filled_heart_icon,
            R.drawable.icon_heart_bottom_navigation
        ),
        NavigationBarItem(
            Destination.SUPPORT,
            R.drawable.filled_support_icon,
            R.drawable.support_icon
        ),
        NavigationBarItem(Destination.CART, R.drawable.filled_cart_icon, R.drawable.icon_cart),
        NavigationBarItem(
            Destination.PROFILE,
            R.drawable.filled_profile_icon,
            R.drawable.icon_profile
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DonutsTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                if (state.isCompleted) {
                    ShowContent(navController, state.destination)
                }
            }
        }


    }

    @Composable
    fun ShowContent(navController: NavHostController, startDestination: String) {
        Scaffold(
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                val visibleItems =
                    items.filter { it.route != Destination.SUPPORT }.map { it.route }
                if (currentDestination.toString() in visibleItems) {
                    GetBottomNavigation(navController = navController, currentDestination)
                }
            }

        ) { paddingValues ->
            CompositionLocalProvider(LocalBottomNavPadding provides paddingValues){
                DonutsNavGraph(navController = navController, startDestination)
            }
        }

    }

    @Composable
    fun GetBottomNavigation(navController: NavController, currentDestination: String?) {
        DonutsBottomNavigation {
            items.fastForEach { item ->
                DonutsBottomNavItem(
                    icon = if (item.route == currentDestination.toString())
                        item.selectedIcon else item.unselectedIcon
                ) {
                    if (item.route != currentDestination.toString()) {
                        // navController.popBackStack()
                        navController.navigate(item.route)
                    }
                }
            }
        }
    }
}
