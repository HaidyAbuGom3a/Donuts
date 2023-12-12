package com.example.donuts.ui.screens.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.donuts.R
import com.example.donuts.ui.composables.CardIcon
import com.example.donuts.ui.radius
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.Typography

@Composable
fun HomeHeadline(headline: String, description: String, hasSearch: Boolean) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                headline,
                style = Typography.headlineMedium,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.spacing_32)
            )

            Text(
                description,
                style = Typography.titleSmall,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.spacing_32)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        if (hasSearch) {
            CardIcon(
                icon = painterResource(id = R.drawable.icon_search),
                shape = RoundedCornerShape(MaterialTheme.radius.radius_15),
                parameter = null,
                containerColor = Primary100,
                contentColor = Primary300,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.spacing_32)
            )

        }
    }
}