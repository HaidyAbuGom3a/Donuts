package com.example.donuts.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donuts.domain.usecases.ManageUserUseCase
import com.example.donuts.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val manageUser: ManageUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.Default) {
            val keepLoggedIn = async { manageUser.getIfLoggedIn() }
            val ifFirstTimeUseApp = async { manageUser.getIfFirstTimeUseApp() }
            val startDestination = when {
                ifFirstTimeUseApp.await() -> Destination.ON_BOARDING
                keepLoggedIn.await() -> Destination.HOME
                else -> Destination.LOGIN
            }
            _state.update { it.copy(destination = startDestination, isCompleted = true )}
        }
    }
}

data class MainUiState(
    val isCompleted: Boolean = false,
    val destination: String = Destination.LOGIN
)