package com.example.donuts.ui.screens.details

import android.util.Log
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
    BaseViewModel<DetailsUiState>(DetailsUiState()) {
    private val args = DetailsArgs(savedStateHandle)

    init {
        getDonutDetails()
    }

    private fun getDonutDetails() {
        val donut: DetailsUiState = getDonutDetailsUseCase(args.id.toInt()).toDetailsUiState()
        //Log.e("haidy",donut.donutName)
        _state.update {
            it.copy(
                donutName = donut.donutName,
                image = donut.image,
                price = donut.price,
                description = donut.description
            )
        }
    }

}