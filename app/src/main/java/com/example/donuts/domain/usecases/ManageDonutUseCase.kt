package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.Cart
import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import javax.inject.Inject

interface IManageDonutUseCase {
    suspend fun addDonutToFavorite(donutId: String)
    suspend fun removeDonutFromFavorite(donutId: String)
    suspend fun getAllFavoriteDonuts(): List<Donut>
    suspend fun getIfDonutIsFavorite(donutId: String): Boolean
    suspend fun addDonutToCart(donutId: String, amount: Int)
    suspend fun getAllCartItems(): Cart
    suspend fun getDonutDetails(donutId: String): Donut
    suspend fun getAllDonuts(): List<Donut>
    suspend fun getAllOffers(): List<Donut>
}

class ManageDonutUseCase @Inject constructor(private val donutRepo: IDonutsRepository) : IManageDonutUseCase {
    override suspend fun addDonutToFavorite(donutId: String) {
        donutRepo.addDonutToFavorite(donutId)
    }

    override suspend fun removeDonutFromFavorite(donutId: String) {
        donutRepo.deleteDonutFromFavorite(donutId)
    }

    override suspend fun getAllFavoriteDonuts(): List<Donut> {
        return donutRepo.getAllFavorites()
    }

    override suspend fun getIfDonutIsFavorite(donutId: String): Boolean {
        return donutRepo.getIfFavorite(donutId)
    }

    override suspend fun addDonutToCart(donutId: String, amount: Int) {
        donutRepo.addDonutToCart(donutId, amount)
    }

    override suspend fun getAllCartItems(): Cart {
        return donutRepo.getCartItems()
    }

    override suspend fun getDonutDetails(donutId: String): Donut {
        return donutRepo.getDonutDetails(donutId)
    }

    override suspend fun getAllDonuts(): List<Donut> {
        return donutRepo.getAllDonuts()
    }

    override suspend fun getAllOffers(): List<Donut> {
        return donutRepo.getAllOffers()
    }

}