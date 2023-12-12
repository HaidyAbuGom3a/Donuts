package com.example.donuts.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.radius
import com.example.donuts.ui.theme.Black
import com.example.donuts.ui.theme.Black60
import com.example.donuts.ui.theme.Grey100
import com.example.donuts.ui.theme.Grey200
import com.example.donuts.ui.theme.Primary300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonutsTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    hintColor: Color = Black60,
    modifier: Modifier = Modifier,
    trailingPainter: Painter? = null,
    leadingPainter: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    radius: Dp = MaterialTheme.radius.radius_16,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    onTrailingIconClick: () -> Unit = {},
    onLeadingIconClick: () -> Unit = {},
    isSingleLine: Boolean = true,
    showPassword: Boolean = false,
    readOnly: Boolean = false,
    trailingIconEnabled: Boolean = onTrailingIconClick != {},
    iconTint: Color? = null,
    outlinedTextFieldDefaults: TextFieldColors = outlinedTextFieldColorDefaults()

) {
    Column(
        modifier = modifier.background(Color.Transparent),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp, max = 160.dp),
            value = text,
            placeholder = {
                Text(
                    hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = hintColor,
                )
            },
            onValueChange = onValueChange,
            shape = RoundedCornerShape(radius),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Black),
            singleLine = isSingleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = servifyVisualTransformation(keyboardType, showPassword),
            isError = isError,
            trailingIcon = {
                trailingPainter?.let {
                    IconButton(
                        onClick = onTrailingIconClick,
                        enabled = trailingIconEnabled,
                    ) {
                        Icon(
                            painter = trailingPainter,
                            contentDescription = "trailing icon",
                            tint = iconTint ?: Black60
                        )
                    }
                }
            },
            leadingIcon = if (leadingPainter != null) {
                {
                    Icon(
                        painter = leadingPainter,
                        contentDescription = "leading icon",
                        tint = iconTint ?: Black60,
                        modifier = Modifier.noRippleEffect(onLeadingIconClick)
                    )
                }
            } else null,
            colors = outlinedTextFieldDefaults,
            readOnly = readOnly
        )
        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red.copy(alpha = 0.6f)
            )
        }
    }

}

@Composable
fun outlinedTextFieldColorDefaults() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = Grey100,
    unfocusedContainerColor = Grey100,
    cursorColor = Primary300,
    focusedBorderColor = Grey200,
    unfocusedBorderColor = Grey100,
)

@Composable
private fun servifyVisualTransformation(
    keyboardType: KeyboardType,
    showPassword: Boolean
): VisualTransformation {
    return if (showPassword || keyboardType != KeyboardType.Password && keyboardType != KeyboardType.NumberPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}

@Composable
@Preview(showSystemUi = false)
private fun DonutsServifyTextField() {
    DonutsTextField(text = "Haidy El Sayed", "Placeholder", onValueChange = {})
}