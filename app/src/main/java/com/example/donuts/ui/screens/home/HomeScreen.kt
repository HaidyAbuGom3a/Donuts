package com.example.donuts.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.ui.screens.details.navigateToDetails
import com.example.donuts.ui.screens.home.composable.HomeHeadline
import com.example.donuts.ui.screens.home.composable.ItemDonut
import com.example.donuts.ui.screens.home.composable.ItemDonutLoading
import com.example.donuts.ui.screens.home.composable.ItemDonutOffer
import com.example.donuts.ui.screens.home.composable.ItemDonutOfferLoading
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.CardBlue
import com.example.donuts.ui.theme.CardPink
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(Primary100, darkIcons = true)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            is HomeUiEffect.NavigateToDonutDetails -> navController.navigateToDetails(effect.id)
        }
    }
    HomeContent(state = state, viewModel)

}

@Composable
fun HomeContent(
    state: HomeUiState,
    listener: HomeInteractionListener
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND)
                .padding(
                    top = paddingValues.calculateTopPadding() + 40.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            HomeHeadline(
                "Let's Gonuts!",
                "Order your favourite donuts from here",
                true
            )
            Text(
                text = "Today Offers",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(start = 32.dp, bottom = 24.dp, top = 40.dp)
            )
            AnimatedVisibility(visible = state.donuts.isEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(4) {
                        ItemDonutOfferLoading()
                    }
                }
            }
            LazyRow(contentPadding = PaddingValues(horizontal = 32.dp)) {
                itemsIndexed(state.offers) { index, item ->
                    val background = if (index % 2 == 0) CardBlue else CardPink
                    ItemDonutOffer(
                        name = item.name,
                        description = item.description,
                        offer = item.price.toString(),
                        oldPrice = item.oldPrice.toString(),
                        isFav = item.isFavorite,
                        background = background,
                        imageUrl = item.image,
                        onClickItem = { listener.onClickItem(item.id) },
                        onClickFav = { listener.onClickFav(index) }
                    )
                }
            }
            Text(
                text = "Donuts",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(start = 32.dp, top = 24.dp)
            )

            AnimatedVisibility(visible = state.isLoading) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(6) {
                        ItemDonutLoading()
                    }
                }
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(state.donuts) { donut ->
                    ItemDonut(
                        name = donut.name,
                        price = donut.price,
                        imagePainter = rememberAsyncImagePainter(model = donut.image),
                        onClickItem = { listener.onClickItem(donut.id) })
                }
            }

        }
    }

}