package com.example.donuts.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import com.example.donuts.domain.usecases.GetDonutDetailsUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDonutDetailsUseCase: GetDonutDetailsUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()), DetailsInteractionListener {
    private val args = DetailsArgs(savedStateHandle)

    init {
        getDonutDetails()
    }

    private fun getDonutDetails() {
        tryToExecute(
            { getDonutDetailsUseCase(args.id).toDetailsUiState() },
            ::onGetDonutDetailsSuccess,
            ::onError
        )

    }

    private fun onGetDonutDetailsSuccess(donut: DetailsUiState) {
        _state.update {
            it.copy(
                donutName = donut.donutName,
                image = donut.image,
                price = donut.price,
                description = donut.description
            )
        }
    }

    private fun onError(e: Exception) {
        println("error in details: $e")
    }

    override fun onClickPlusButton() {
        val newQuantity = _state.value.quantity + 1
        _state.update { it.copy(quantity = newQuantity) }
    }

    override fun onClickMinusButton() {
        if (_state.value.quantity > 1) {
            val newQuantity = _state.value.quantity - 1
            _state.update { it.copy(quantity = newQuantity) }
        }
    }

    override fun onClickAddToCart() {
        sendNewEffect(DetailsUiEffect.ShowAddToCartMessage)
    }

    override fun onClickBackIcon() {
        sendNewEffect(DetailsUiEffect.NavigateUp)
    }

    override fun onClickFav() {
        _state.update { it.copy(isFavorite = !it.isFavorite) }
    }

}