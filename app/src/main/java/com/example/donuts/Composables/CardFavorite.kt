package com.example.donuts.Composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.White

@Composable
fun CardFavorite(icon: Painter, shape: Shape,id:Int , onClickFav: (Int) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = Primary300
        ),
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.spacing_16,
            top = MaterialTheme.spacing.spacing_16
        )
            .clickable { onClickFav(id) },
        shape = shape
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.padding(MaterialTheme.spacing.spacing_8)
        )
    }
}