package com.example.donuts.ui.screens.details.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.donuts.R
import com.example.donuts.ui.composables.CardIcon
import com.example.donuts.ui.composables.CardText
import com.example.donuts.ui.composables.PrimaryButton
import com.example.donuts.ui.modifier.shimmerEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.screens.details.DetailsInteractionListener
import com.example.donuts.ui.theme.Black
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White

@Composable
fun DonutDetailsCard(
    donutName: String,
    description: String,
    isFavorite: Boolean,
    price: Double,
    quantity: Int,
    listener: DetailsInteractionListener,
    isFavLoading: Boolean,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Text(
                donutName,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(start = 32.dp, top = 32.dp, bottom = 32.dp)
            )
            Text(
                "About Gonut",
                style = Typography.titleLarge,
                modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
            )
            Text(
                description,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 24.dp)
            )
            Text(
                "Quantity",
                style = Typography.titleLarge,
                modifier = Modifier.padding(start = 32.dp, bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .fillMaxWidth()
            ) {
                CardText(
                    text = "-",
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    isClickable = true,
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                    containerColor = White,
                    contentColor = Black,
                    onClick = { listener.onClickMinusButton() },
                    modifier = Modifier.padding(end = 24.dp)
                )
                CardText(
                    text = quantity.toString(),
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                    isClickable = false,
                    containerColor = White,
                    contentColor = Black,
                    modifier = Modifier.padding(end = 24.dp)
                )
                CardText(
                    text = "+",
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
                    isClickable = true,
                    containerColor = Black,
                    contentColor = White,
                    onClick = { listener.onClickPlusButton() },
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "£" + (price * quantity).toString(),
                    style = Typography.displayLarge,
                    modifier = Modifier.padding(start = 32.dp, end = 24.dp)
                )
                PrimaryButton(
                    onClick = { listener.onClickAddToCart() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary300,
                        contentColor = White
                    ),
                    text = "Add to Cart",
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }
        val icon = if (!isFavorite) R.drawable.icon_heart_add_to_favourite else
            R.drawable.icon_heart_filled

        if (!isFavLoading) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                CardIcon(
                    icon = painterResource(id = icon),
                    shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                    onClick = { listener.onClickFav() },
                    modifier = Modifier.padding(end = 32.dp, top = 335.dp),
                    contentPadding = 12.dp,
                    elevation = CardDefaults.cardElevation(1.dp)
                )
            }
        }

    }

    if (isFavLoading) {
        FavCardLoading()
    }
}

@Composable
private fun FavCardLoading() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 32.dp, top = 335.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(MaterialTheme.radius.radius_15))
                .shimmerEffect()
        )
    }
}