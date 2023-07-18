package com.example.donuts.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T>(state: T) : ViewModel() {
    protected val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

}