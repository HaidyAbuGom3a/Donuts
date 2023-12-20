package org.haidy.support.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun <T> EffectHandler(
    effects: Flow<T>,
    handleEffect: (T) -> Unit
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = effects) {
        scope.launch {
            effects.collectLatest { effect ->
                handleEffect(effect)
            }
        }
    }
}