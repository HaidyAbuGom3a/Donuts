package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import javax.inject.Inject

class GetAllDonutsUseCase @Inject constructor(
    private val donutsRepo: IDonutsRepository
) {
    suspend operator fun invoke(): List<Donut> =
        donutsRepo.getAllDonuts()
}