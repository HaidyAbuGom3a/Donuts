package com.example.donuts.ui.screens.cart

import com.example.donuts.domain.entities.Cart
import com.example.donuts.domain.usecases.ManageDonutUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val manageDonut: ManageDonutUseCase) :
    BaseViewModel<CartUiState, CartUiEffect>(CartUiState()), CartInteractionListener {
    init {
        getCart()
    }

    private fun getCart() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageDonut.getAllCartItems() },
            ::onGetCartSuccess,
            ::onError
        )
    }

    private fun onGetCartSuccess(cart: Cart) {
        val cartUiState = cart.toUiState()
        _state.update { it.copy(items = cartUiState.items, totalPrice = cartUiState.totalPrice, isLoading = false) }
    }

    private fun onError(e: Exception) {
        _state.update { it.copy(isLoading = false) }
        println("error occurred $e")
    }

    override fun onClickOrderNow() {
        sendNewEffect(CartUiEffect.ShowToastMessage("Ordered Successfully"))
    }

    override fun onClickBackIcon() {
        sendNewEffect(CartUiEffect.NavigateUp)
    }
}