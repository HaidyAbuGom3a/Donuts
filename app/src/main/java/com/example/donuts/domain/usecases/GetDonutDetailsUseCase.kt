package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import javax.inject.Inject

class GetDonutDetailsUseCase @Inject constructor(
    private val donutsRepo: IDonutsRepository
) {
    suspend operator fun invoke(donutId: String): Donut {
        return donutsRepo.getDonutDetails(donutId)
    }
}