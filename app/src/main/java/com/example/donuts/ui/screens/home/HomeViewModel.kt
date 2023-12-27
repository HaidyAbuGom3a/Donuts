package com.example.donuts.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.usecases.ManageDonutUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val manageDonut: ManageDonutUseCase,
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    init {
        getAllFavorites()

    }

    private fun getAllFavorites() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageDonut.getAllFavoriteDonuts() },
            ::onGetAllFavoritesSuccess,
            ::onError
        )
    }

    private fun onGetAllFavoritesSuccess(donuts: List<Donut>) {
        _state.update { it.copy(favorites = donuts.map { donut -> donut.id }) }
        viewModelScope.launch(Dispatchers.Default) {
            getAllDonuts()
            getAllOffers()
        }
    }

    private fun getAllDonuts() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageDonut.getAllDonuts().map { it.toDonutUiState() } },
            ::onGetDonutsSuccess,
            ::onError
        )
    }

    private fun getAllOffers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageDonut.getAllOffers().map { it.toDonutUiState() } },
            ::onGetOffersSuccess,
            ::onError
        )
    }

    override fun onClickFav(itemIndex: Int) {
        val item = state.value.offers[itemIndex]
        val isFav = item.isFavorite
        if (isFav) {
            tryToExecute(
                { manageDonut.removeDonutFromFavorite(item.id) },
                {
                    val offers = state.value.offers.mapIndexed { index, item ->
                        if (index == itemIndex) item.copy(isFavorite = false) else item
                    }
                    _state.update { it.copy(offers = offers) }
                },
                ::onError
            )
        } else {
            tryToExecute(
                { manageDonut.addDonutToFavorite(item.id) },
                {
                    val offers = state.value.offers.mapIndexed { index, item ->
                        if (index == itemIndex) item.copy(isFavorite = true) else item
                    }
                    _state.update { it.copy(offers = offers) }
                },
                ::onError
            )
        }
    }

    override fun onClickItem(id: String) {
        sendNewEffect(HomeUiEffect.NavigateToDonutDetails(id))
    }

    private fun onGetDonutsSuccess(donuts: List<DonutUiState>) {
        _state.update { it.copy(donuts = donuts, isLoading = false) }
    }

    private fun onGetOffersSuccess(offers: List<DonutUiState>) {
        val updatedOffers =
            offers.map { donut -> donut.copy(isFavorite = donut.id in state.value.favorites) }
        _state.update { it.copy(offers = updatedOffers, isLoading = false) }
    }

    private fun onError(e: Exception) {
        _state.update { it.copy(isLoading = false) }
    }

}