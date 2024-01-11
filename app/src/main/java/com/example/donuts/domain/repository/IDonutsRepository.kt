package com.example.donuts.domain.repository

import com.example.donuts.domain.entities.Cart
import com.example.donuts.domain.entities.Donut

interface IDonutsRepository {
    suspend fun getAllDonuts(): List<Donut>

    suspend fun getAllOffers(): List<Donut>

    suspend fun getDonutDetails(id: String): Donut

    suspend fun addDonutToFavorite(id: String)

    suspend fun deleteDonutFromFavorite(id: String)

    suspend fun addDonutToCart(id: String, amount: Int)

    suspend fun getCartItems(): Cart

    suspend fun getAllFavorites(): List<Donut>

    suspend fun getIfFavorite(id: String): Boolean
}