package com.example.donuts.ui.screens.favorite

import com.example.donuts.domain.usecases.ManageDonutUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val manageDonut: ManageDonutUseCase) :
    BaseViewModel<FavoriteUiState, FavoriteUiEffect>(
        FavoriteUiState()
    ), FavoriteInteractionListener {
    init {
        getAllFavoriteItems()
    }

    private fun getAllFavoriteItems() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageDonut.getAllFavoriteDonuts() },
            { items ->
                _state.update {
                    it.copy(
                        items = items.toFavoriteUiState().items,
                        isLoading = false
                    )
                }
            },
            { e -> _state.update { it.copy(isLoading = false) } }
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(FavoriteUiEffect.NavigateUp)
    }

    override fun onClickItem(id: String) {
        sendNewEffect(FavoriteUiEffect.NavigateToItem(id))
    }

}