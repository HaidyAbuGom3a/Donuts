package com.example.donuts.data.local

import com.example.donuts.domain.entities.DonutEntity

interface DataSource {
    fun getAllDonuts():List<DonutEntity>
    fun getAllOffers():List<DonutEntity>
}