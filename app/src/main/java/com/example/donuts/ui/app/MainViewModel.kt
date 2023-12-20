package com.example.donuts.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donuts.domain.usecases.ManageUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val manageUser: ManageUserUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val keepLoggedIn = manageUser.getIfLoggedIn()
            val ifFirstTimeUseApp = manageUser.getIfFirstTimeUseApp()
            println("haidy found the result keeplogged $keepLoggedIn firsttime $ifFirstTimeUseApp")
            _state.update { it.copy(keepLoggedIn = keepLoggedIn, ifFirstTimeUseApp) }
        }
    }

}

data class MainUiState(
    val keepLoggedIn: Boolean = false,
    val ifFirstTimeUseApp: Boolean = false,
)