package com.example.donuts.data.fake

import com.example.donuts.domain.entities.Donut

interface IDataSource {
    suspend fun getAllDonuts():List<Donut>
    suspend fun getAllOffers():List<Donut>
}