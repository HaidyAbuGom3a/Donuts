package com.example.donuts.ui.screens.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.Composables.CardIcon
import com.example.donuts.Composables.CardText
import com.example.donuts.Composables.HorizontalSpacer24
import com.example.donuts.Composables.PrimaryButton
import com.example.donuts.Composables.VerticalSpacer16
import com.example.donuts.Composables.VerticalSpacer24
import com.example.donuts.Composables.VerticalSpacer32
import com.example.donuts.R
import com.example.donuts.ui.dimens
import com.example.donuts.ui.radius
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Black
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White
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
    val toastMsg = stringResource(R.string.added_successfully)
    DetailsContent(
        state = state,
        onClickMinus = viewModel::onClickMinusButton,
        onClickPlus = viewModel::onClickPlusButton,
        onClickAddToCart = {
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
        },
        onClickFav = viewModel::onClickFav
    ) {
        navController.navigateUp()
    }
}

@Composable
fun DetailsContent(
    state: DetailsUiState,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit,
    onClickAddToCart: () -> Unit,
    onClickFav: () -> Unit,
    onClickBack: () -> Unit,
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
            painter = painterResource(state.image),
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
                .padding(
                    start = MaterialTheme.spacing.spacing_32,
                    top = MaterialTheme.spacing.spacing_32
                )
                .clickable { onClickBack() },
            tint = Primary300
        )
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        val icon = if (!state.isFavorite) R.drawable.icon_heart_add_to_favourite else
            R.drawable.icon_heart_filled
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.spacing_350),
            shape = RoundedCornerShape(
                topStart = MaterialTheme.radius.radius_40,
                topEnd = MaterialTheme.radius.radius_40
            ),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Text(
                state.donutName,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.spacing_32,
                    top = MaterialTheme.spacing.spacing_32
                )
            )
            VerticalSpacer32()
            Text(
                stringResource(R.string.about_gonut),
                style = Typography.titleLarge,
                modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32)
            )
            VerticalSpacer16()
            Text(
                state.description,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.spacing_32)
            )
            VerticalSpacer24()
            Text(
                stringResource(R.string.quantity),
                style = Typography.titleLarge,
                modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32)
            )
            VerticalSpacer16()
            Row(
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.spacing_32)
                    .fillMaxWidth()
            ) {
                CardText(
                    text = stringResource(R.string.minus),
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    isClickable = true,
                    contentPadding = PaddingValues(
                        vertical = MaterialTheme.spacing.spacing_8,
                        horizontal = MaterialTheme.spacing.spacing_16
                    ),
                    containerColor = White,
                    contentColor = Black,
                    onClick = onClickMinus
                )
                HorizontalSpacer24()
                CardText(
                    text = state.quantity.toString(),
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    contentPadding = PaddingValues(
                        vertical = MaterialTheme.spacing.spacing_8,
                        horizontal = MaterialTheme.spacing.spacing_16
                    ),
                    isClickable = false,
                    containerColor = White,
                    contentColor = Black
                )
                HorizontalSpacer24()
                CardText(
                    text = stringResource(R.string.plus),
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    contentPadding = PaddingValues(
                        vertical = MaterialTheme.spacing.spacing_8,
                        horizontal = MaterialTheme.spacing.spacing_16
                    ),
                    isClickable = true,
                    containerColor = Black,
                    contentColor = White,
                    onClick = onClickPlus
                )
            }
            VerticalSpacer24()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.price) + state.price.toString(),
                    style = Typography.displayLarge,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32)
                )
                HorizontalSpacer24()
                PrimaryButton(
                    onClick = onClickAddToCart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary300,
                        contentColor = White
                    ),
                    text = stringResource(
                        R.string.add_to_cart
                    ),
                    contentPadding = PaddingValues(
                        vertical = MaterialTheme.spacing.spacing_16,
                        horizontal = MaterialTheme.spacing.spacing_24
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = MaterialTheme.spacing.spacing_32)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            CardIcon(
                icon = painterResource(id = icon),
                shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                parameter = null,
                onClick = onClickFav,
                modifier = Modifier.padding(
                    end = MaterialTheme.spacing.spacing_32,
                    top = MaterialTheme.spacing.spacing_335
                ),
                contentPadding = MaterialTheme.spacing.spacing_12,
                elevation = CardDefaults.cardElevation(MaterialTheme.spacing.spacing_1)
            )
        }

    }

}