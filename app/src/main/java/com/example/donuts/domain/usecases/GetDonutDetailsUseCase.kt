package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.DonutEntity
import com.example.donuts.domain.repository.Repository
import javax.inject.Inject

class GetDonutDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(donutId: Int): DonutEntity =
        repository.getAllDonuts().find { it.id == donutId } ?: repository.getAllOffers()
            .find { it.id == donutId }!!
}