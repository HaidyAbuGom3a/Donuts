package com.example.donuts.domain.repository

import com.example.donuts.domain.entities.DonutEntity

interface Repository {
    fun getAllDonuts(): List<DonutEntity>
    fun getAllOffers(): List<DonutEntity>
}