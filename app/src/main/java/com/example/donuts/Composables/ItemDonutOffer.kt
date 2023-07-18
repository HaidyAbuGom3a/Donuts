package com.example.donuts.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.example.donuts.R
import com.example.donuts.ui.dimens
import com.example.donuts.ui.radius
import com.example.donuts.ui.screens.home.DonutUiState
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Black60
import com.example.donuts.ui.theme.Typography

@Composable
fun ItemDonutOffer(
    state: DonutUiState,
    background: Color,
    onClickItem: (Int) -> Unit,
    onClickFav: (Int) -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .height(MaterialTheme.dimens.dimens_280)
                .width(MaterialTheme.dimens.dimens_173)
                .clickable { onClickItem(state.id) },
            colors = CardDefaults.cardColors(containerColor = background),
            shape = RoundedCornerShape(MaterialTheme.radius.radius_20)
        ) {
            Column {
                val icon = if (state.isFavorite) R.drawable.icon_heart_filled
                else R.drawable.icon_heart_add_to_favourite
                CardFavorite(
                    icon = painterResource(id = icon),
                    shape = CircleShape,
                    state.id,
                    onClickFav
                )
                VerticalSpacer118()
                Text(
                    state.name,
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_16)
                )
                Text(
                    state.description,
                    style = Typography.bodySmall,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.spacing_16)
                        .padding(top = MaterialTheme.spacing.spacing_8),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        stringResource(R.string.dollar) + state.oldPrice.toString(),
                        style = Typography.displaySmall,
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.SemiBold,
                        color = Black60,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.spacing_16),
                    )
                    Text(
                        stringResource(R.string.dollar) + state.newPrice.toString(),
                        style = Typography.displayMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                top = MaterialTheme.spacing.spacing_8,
                                end = MaterialTheme.spacing.spacing_16,
                                start = MaterialTheme.spacing.spacing_4
                            ),
                    )
                }

            }
        }
        Column {
            VerticalSpacer40()
            Row {
                HorizontalSpacer84()
                Image(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.dimens_130),
                    painter = painterResource(id = state.image),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        }
    }
}