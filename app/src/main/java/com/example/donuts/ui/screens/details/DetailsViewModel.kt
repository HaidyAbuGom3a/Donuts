package com.example.donuts.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.donuts.domain.usecases.ManageDonutUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val manageDonut: ManageDonutUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()), DetailsInteractionListener {
    private val args = DetailsArgs(savedStateHandle)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getDonutDetails()
            getIfDonutIsFavorite(args.id)
        }
    }

    private fun getIfDonutIsFavorite(id: String) {
        _state.update { it.copy(isFavLoading = true) }
        tryToExecute(
            { manageDonut.getIfDonutIsFavorite(id) },
            ::onGetIfFavoriteSuccess,
            ::onError
        )
    }

    private fun onGetIfFavoriteSuccess(isFav: Boolean) {
        _state.update { it.copy(isFavorite = isFav, isFavLoading = false) }
    }

    private fun getDonutDetails() {
        tryToExecute(
            { manageDonut.getDonutDetails(args.id).toDetailsUiState() },
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
        _state.update { it.copy(isFavLoading = false) }
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
        tryToExecute(
            { manageDonut.addDonutToCart(args.id, state.value.quantity) },
            {
                sendNewEffect(DetailsUiEffect.ShowAddToCartMessage)
                sendNewEffect(DetailsUiEffect.NavigateToCart)
            },
            ::onError
        )
    }

    override fun onClickBackIcon() {
        sendNewEffect(DetailsUiEffect.NavigateUp)
    }

    override fun onClickFav() {
        _state.update { it.copy(isFavLoading = true) }
        if (state.value.isFavorite) {
            tryToExecute(
                { manageDonut.removeDonutFromFavorite(args.id) },
                { _state.update { it.copy(isFavLoading = false, isFavorite = false) } },
                ::onError
            )
        } else {
            tryToExecute(
                { manageDonut.addDonutToFavorite(args.id) },
                { _state.update { it.copy(isFavLoading = false, isFavorite = true) } },
                ::onError
            )
        }
    }

}