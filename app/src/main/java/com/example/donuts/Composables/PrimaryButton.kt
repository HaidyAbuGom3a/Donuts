package com.example.donuts.Composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.donuts.ui.theme.Typography

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    contentPadding:PaddingValues = ButtonDefaults.ContentPadding,
    text: String
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        Text(
            text ,
            style = Typography.labelLarge,
        )
    }
}