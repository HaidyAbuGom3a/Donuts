package org.haidy.support.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.haidy.support.ui.modifier.noRippleEffect
import org.haidy.support.ui.theme.Primary300


@Composable
fun CardText(
    text: String,
    shape: Shape,
    modifier: Modifier = Modifier,
    isClickable: Boolean,
    onClick: () -> Unit = {},
    containerColor: Color = White,
    contentColor: Color = Primary300,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    val newModifier = if (isClickable) modifier.noRippleEffect { onClick() } else modifier
    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = newModifier,
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text,
            modifier = Modifier.padding(contentPadding),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}