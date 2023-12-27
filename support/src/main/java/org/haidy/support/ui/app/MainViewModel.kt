package org.haidy.support.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.support.domain.usecases.ManageUserUseCase
import org.haidy.support.ui.navigation.SupportDestination
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val manageUser: ManageUserUseCase) : ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getIfLoggedIn()
    }

    private fun getIfLoggedIn() {
        viewModelScope.launch(Dispatchers.Default) {
            val keepLoggedIn = async { manageUser.getIfLoggedIn() }
            if (keepLoggedIn.await()) {
                _state.update { it.copy(destination = SupportDestination.CHAT, isCompleted = true) }
            } else {
                _state.update { it.copy(destination = SupportDestination.LOGIN, isCompleted = true) }
            }
        }
    }
}

data class MainUiState(
    val isCompleted: Boolean = false,
    val destination: String = SupportDestination.LOGIN
)