package com.example.donuts.ui.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.dimens
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.screens.home.DonutUiState
import com.example.donuts.ui.spacing
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
                .width(MaterialTheme.dimens.dimens_138)
                .padding(top = MaterialTheme.spacing.spacing_24),
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
                VerticalSpacer32()
                Text(
                    state.name,
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.spacing_8)
                )
                VerticalSpacer8()
                Text(
                    text = stringResource(id = R.string.dollar)
                            + state.price.toString(),
                    style = Typography.displaySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary300
                )
                VerticalSpacer24()
            }

        }

        Image(
            painter = rememberAsyncImagePainter(model = state.image),
            contentDescription = "",
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.spacing_84)
                .width(MaterialTheme.dimens.dimens_104)
                .height(MaterialTheme.dimens.dimens_112)
        )
    }
}