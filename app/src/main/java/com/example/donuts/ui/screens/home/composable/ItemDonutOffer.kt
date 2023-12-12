package com.example.donuts.ui.screens.home.composable

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.composables.CardIcon
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.screens.home.DonutUiState
import com.example.donuts.ui.theme.Black60
import com.example.donuts.ui.theme.Typography

@Composable
fun ItemDonutOffer(
    state: DonutUiState,
    background: Color,
    onClickItem: (String) -> Unit,
    onClickFav: (String) -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .height(280.dp)
                .width(173.dp)
                .noRippleEffect { onClickItem(state.id) },
            colors = CardDefaults.cardColors(containerColor = background),
            shape = RoundedCornerShape(MaterialTheme.radius.radius_20)
        ) {
            Column {
                val icon = if (state.isFavorite) R.drawable.icon_heart_filled
                else R.drawable.icon_heart_add_to_favourite
                CardIcon(
                    icon = painterResource(id = icon),
                    shape = CircleShape,
                    state.id,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp
                    ),
                    onClickFav
                )
                Text(
                    state.name,
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 118.dp)
                )
                Text(
                    state.description,
                    style = Typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "$" + state.oldPrice.toString(),
                        style = Typography.displaySmall,
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.SemiBold,
                        color = Black60,
                        modifier = Modifier
                            .padding(top = 16.dp),
                    )
                    Text(
                        "$" + state.price.toString(),
                        style = Typography.displayMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp, end = 16.dp, start = 4.dp),
                    )
                }

            }
        }
        Box(Modifier.padding(top = 40.dp, start = 84.dp)) {
            Image(
                modifier = Modifier.size(130.dp),
                painter = rememberAsyncImagePainter(model = state.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}