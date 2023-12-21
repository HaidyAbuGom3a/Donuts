package com.example.donuts.ui.screens.home

import com.example.donuts.domain.usecases.GetAllDonutsUseCase
import com.example.donuts.domain.usecases.GetOffersUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDonutsUseCase: GetAllDonutsUseCase,
    private val getOffersUseCase: GetOffersUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    init {
        getAllDonuts()
        getAllOffers()
    }

    private fun getAllDonuts() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getAllDonutsUseCase().map { it.toDonutUiState() } },
            ::onGetDonutsSuccess,
            ::onError
        )
    }

    private fun getAllOffers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getOffersUseCase().map { it.toDonutUiState() } },
            ::onGetOffersSuccess,
            ::onError
        )
    }

    override fun onClickFav(id: String) {
        val isFavorite = state.value.offers.find { it.id == id }!!.isFavorite
        _state.update {
            it.copy(
                offers = it.offers.map { offer ->
                    if (offer.id == id) {
                        offer.copy(isFavorite = !isFavorite)
                    } else {
                        offer
                    }
                }
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
        _state.update { it.copy(offers = offers, isLoading = false) }
    }

    private fun onError(e: Exception) {
        println("haidy error $e")
        _state.update { it.copy(isLoading = false) }
    }

}