package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.DonutEntity
import com.example.donuts.domain.repository.Repository
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): List<DonutEntity> =
        repository.getAllOffers()
}