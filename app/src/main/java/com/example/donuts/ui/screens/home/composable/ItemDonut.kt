package com.example.donuts.ui.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.screens.home.DonutUiState
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White


@Composable
fun ItemDonut(state: DonutUiState, onClickItem: (String) -> Unit) {
    Box(
        modifier = Modifier.noRippleEffect { onClickItem(state.id) },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(138.dp)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(
                topStart = MaterialTheme.radius.radius_20,
                topEnd = MaterialTheme.radius.radius_20,
                bottomStart = MaterialTheme.radius.radius_10,
                bottomEnd = MaterialTheme.radius.radius_10
            ),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    state.name,
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
                )
                Text(
                    text = "$"
                            + state.price.toString(),
                    style = Typography.displaySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary300,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

        }

        Image(
            painter = rememberAsyncImagePainter(model = state.image),
            contentDescription = "",
            modifier = Modifier
                .padding(bottom = 84.dp)
                .width(104.dp)
                .height(112.dp)
        )
    }
}