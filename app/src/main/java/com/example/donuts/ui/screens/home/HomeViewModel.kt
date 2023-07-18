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
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        getHomeData()
    }

    private fun getHomeData() {
        val donuts: List<DonutUiState> = getAllDonutsUseCase().map { it.toDonutUiState() }
        val offers: List<DonutUiState> = getOffersUseCase().map { it.toDonutUiState() }
        _state.update {
            it.copy(
                donuts = donuts,
                offers = offers
            )
        }
    }

    fun onClickFav(id: Int) {
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

}