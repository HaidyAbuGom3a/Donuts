package com.example.donuts.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.Composables.BottomNavigation
import com.example.donuts.Composables.HomeHeadline
import com.example.donuts.Composables.ItemDonut
import com.example.donuts.Composables.ItemDonutOffer
import com.example.donuts.Composables.VerticalSpacer16
import com.example.donuts.Composables.VerticalSpacer24
import com.example.donuts.Composables.VerticalSpacer32
import com.example.donuts.Composables.VerticalSpacer40
import com.example.donuts.Composables.VerticalSpacer8
import com.example.donuts.R
import com.example.donuts.ui.screens.details.navigateToDetails
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.CardBlue
import com.example.donuts.ui.theme.CardPink
import com.example.donuts.ui.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Transparent)
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = true)
    val state by viewModel.state.collectAsState()
    HomeContent(
        state = state,
        { id -> navController.navigateToDetails(id) },
        onClickFav = viewModel::onClickFav
    )

}

@Composable
fun HomeContent(
    state: HomeUiState,
    onClickItem: (Int) -> Unit,
    onClickFav: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        VerticalSpacer40()
        HomeHeadline(
            stringResource(R.string.let_s_gonuts),
            stringResource(R.string.order_your_favourite_donuts_from_here),
            true
        )
        VerticalSpacer40()
        Text(
            text = stringResource(R.string.today_offers),
            style = Typography.headlineSmall,
            modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32)
        )
        VerticalSpacer24()
        LazyRow(contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.spacing_32)) {
            itemsIndexed(state.offers) { index, item ->
                val background = if (index % 2 == 0) CardBlue else CardPink
                ItemDonutOffer(
                    state = item,
                    background,
                    onClickItem = onClickItem,
                    onClickFav = onClickFav
                )
            }
        }
        VerticalSpacer32()
        Text(
            text = stringResource(R.string.donuts),
            style = Typography.headlineSmall,
            modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32)
        )
        VerticalSpacer16()
        LazyRow(
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.spacing_32),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing_24)
        ) {
            items(state.donuts) {
                ItemDonut(state = it, onClickItem = onClickItem)
            }
        }
        BottomNavigation(
            icon1 = R.drawable.icon_home,
            icon2 = R.drawable.icon_heart_bottom_navigation,
            icon3 = R.drawable.icon_notification,
            icon4 = R.drawable.icon_cart,
            icon5 = R.drawable.icon_profile
        )
        VerticalSpacer8()

    }

}

@Preview(showSystemUi = true)
@Composable
fun previewHome() {
    HomeScreen(navController = NavController(LocalContext.current))
}