package com.example.donuts.ui.screens.cart

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.app.LocalBottomNavPadding
import com.example.donuts.ui.composables.PrimaryButton
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.cart.composable.ItemCart
import com.example.donuts.ui.screens.cart.composable.ItemCartLoading
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(Primary100, darkIcons = true)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            CartUiEffect.NavigateUp -> navController.popBackStack()
            is CartUiEffect.ShowToastMessage -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    CartContent(state = state, listener = viewModel)
}

@Composable
fun CartContent(state: CartUiState, listener: CartInteractionListener) {
    val bottomNavigationPadding = LocalBottomNavPadding.current
    Scaffold(
        bottomBar = {
            if (state.items.isNotEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Total Price: ${state.totalPrice} £",
                        style = Typography.displayMedium,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                    )
                    PrimaryButton(
                        onClick = { listener.onClickOrderNow() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary300,
                            contentColor = White
                        ),
                        text = "Order Now",
                        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 32.dp,
                                end = 32.dp,
                                bottom = 16.dp + bottomNavigationPadding.calculateBottomPadding()
                            )
                    )

                }
            }
        },
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
                    text = "Cart",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND)
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 8.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (state.items.isEmpty()) Arrangement.Center else Arrangement.Top
        ) {
            items(state.items) { item ->
                ItemCart(
                    name = item.name,
                    amount = item.amount,
                    imagePainter = rememberAsyncImagePainter(
                        model = item.imageUrl
                    )
                )
            }
            items(10) {
                AnimatedVisibility(visible = state.isLoading) {
                    ItemCartLoading()
                }
            }
            item {
                AnimatedVisibility(state.items.isEmpty()) {
                    Image(
                        painterResource(R.drawable.empty_cart_placeholder),
                        contentDescription = null,
                        modifier = Modifier.padding(32.dp)
                    )

                }
            }
            item {
                AnimatedVisibility(state.items.isEmpty()) {
                    Text(
                        "Your cart is Empty!\nGo check some delicious donuts! ",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )

                }
            }

        }
    }
}