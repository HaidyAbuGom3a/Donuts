package com.example.donuts.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donuts.ui.radius
import com.example.donuts.ui.theme.Primary300

@Composable
fun DonutsButton(
    onClick: () -> Unit,
    text: String,
    iconPainter: Painter? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = Primary300,
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 16.sp,
    isLoading: Boolean = false
) {
    Button(
        { onClick() },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(MaterialTheme.radius.radius_16),
        enabled = enabled,
        elevation = elevation
    ) {

        AnimatedVisibility(visible = iconPainter != null) {
            Image(painter = iconPainter!!, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview(showSystemUi = false)
private fun PreviewDonutsButton() {
    DonutsButton(onClick = { }, "Get Started")
}