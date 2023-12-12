package com.example.donuts.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.profile.navigateToProfile
import com.example.donuts.ui.theme.Primary300

@Composable
fun BottomNavigation(
    icon1: Int,
    icon2: Int,
    icon3: Int,
    icon4: Int,
    icon5: Int,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = icon1),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon2),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon3),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon4),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon5),
            tint = Primary300,
            contentDescription = "",
            modifier = Modifier.noRippleEffect { navController.navigateToProfile()}
        )
    }

}