package com.example.donuts.domain.repository

import com.example.donuts.domain.entities.Donut

interface IDonutsRepository {
    suspend fun getAllDonuts(): List<Donut>
    suspend fun getAllOffers(): List<Donut>

    suspend fun getDonutDetails(id: String): Donut
}