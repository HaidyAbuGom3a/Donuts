package com.example.donuts.ui.screens.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.dimens
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.details.composable.DonutDetailsCard
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Primary100, darkIcons = true)
    val context = LocalContext.current
    val toastMsg = "Added Successfully"
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            DetailsUiEffect.NavigateUp -> navController.popBackStack()
            DetailsUiEffect.ShowAddToCartMessage -> {
                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
            }

        }
    }
    DetailsContent(state = state, viewModel)
}

@Composable
fun DetailsContent(
    state: DetailsUiState,
    listener: DetailsInteractionListener
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.dimens_370)
            .background(
                Primary100
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(state.image),
            "",
            modifier = Modifier.size(MaterialTheme.dimens.dimens_330),
            contentScale = ContentScale.FillWidth
        )
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 32.dp, top = 32.dp)
                .noRippleEffect { listener.onClickBackIcon() },
            tint = Primary300
        )
    }
    DonutDetailsCard(
        donutName = state.donutName,
        description = state.description,
        isFavorite = state.isFavorite,
        price = state.price,
        quantity = state.quantity,
        listener = listener
    )

}