package com.example.donuts.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Primary300

@Composable
fun DonutsBottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = MaterialTheme.radius.radius_20,
                    topEnd = MaterialTheme.radius.radius_20
                )
            )
            .background(Primary100)
            .padding(top = 12.dp)

    ) {
        content()
    }

}

@Composable
fun DonutsBottomNavItem(icon: Int, onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = icon),
        tint = Primary300,
        contentDescription = "",
        modifier = Modifier.noRippleEffect { onClick() }
    )
}