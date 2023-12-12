package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val donutsRepo: IDonutsRepository
) {
    suspend operator fun invoke(): List<Donut> {
        return donutsRepo.getAllOffers()
    }

}