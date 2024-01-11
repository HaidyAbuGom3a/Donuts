package com.example.donuts.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.app.LocalBottomNavPadding
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.details.navigateToDetails
import com.example.donuts.ui.screens.home.composable.ItemDonut
import com.example.donuts.ui.screens.home.composable.ItemDonutLoading
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            is FavoriteUiEffect.NavigateToItem -> navController.navigateToDetails(effect.id)
            FavoriteUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    FavoriteContent(state = state, listener = viewModel)
}

@Composable
fun FavoriteContent(state: FavoriteUiState, listener: FavoriteInteractionListener) {
    val bottomNavPadding = LocalBottomNavPadding.current
    Scaffold(
        topBar = {
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            top = 24.dp,
                            bottom = 24.dp
                        )
                        .size(24.dp)
                        .noRippleEffect { listener.onClickBackIcon() },
                    tint = Primary300
                )
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomNavPadding.calculateBottomPadding())
                .background(
                    BACKGROUND
                ),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 16.dp
            ),
            horizontalArrangement = Arrangement.Center
        ) {
            items(state.items) { item ->
                ItemDonut(
                    onClickItem = { listener.onClickItem(item.id) },
                    name = item.name,
                    imagePainter = rememberAsyncImagePainter(model = item.imageUrl)
                )
            }
            items(10) {
                if (state.isLoading) {
                    ItemDonutLoading()
                }
            }
        }
    }
}