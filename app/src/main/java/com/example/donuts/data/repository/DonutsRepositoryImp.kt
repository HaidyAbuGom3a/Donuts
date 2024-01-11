package com.example.donuts.data.repository

import android.content.res.Resources.NotFoundException
import com.example.donuts.data.dto.CartDto
import com.example.donuts.data.dto.CartItemDto
import com.example.donuts.data.dto.DonutDto
import com.example.donuts.data.dto.FavoriteDto
import com.example.donuts.data.local.IDataStore
import com.example.donuts.data.mapper.toEntity
import com.example.donuts.domain.entities.Cart
import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class DonutsRepositoryImp @Inject constructor(
    private val dataStore: IDataStore,
    private val firestore: FirebaseFirestore
) : IDonutsRepository {
    override suspend fun getAllDonuts(): List<Donut> {
        return try {
            firestore.collection(DONUTS)
                .get()
                .await()
                .map { it.toObject<DonutDto>().toEntity() }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }


    override suspend fun getAllOffers(): List<Donut> {
        return getAllDonuts().filter { it.hasOffer }
    }

    override suspend fun getDonutDetails(id: String): Donut {
        return try {
            firestore.collection(DONUTS)
                .document(id)
                .get()
                .await().toObject<DonutDto>()?.toEntity() ?: throw NotFoundException()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun addDonutToFavorite(id: String) {
        val userId = dataStore.getUserId()
        try {
            val favDocumentRef = firestore.collection(FAVORITES).document()
            favDocumentRef.set(
                FavoriteDto(
                    userId = userId,
                    donutId = id
                )
            ).await()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun deleteDonutFromFavorite(id: String) {
        val userId = dataStore.getUserId()
        try {
            val favQuery = firestore.collection(FAVORITES)
                .whereEqualTo("donutId", id)
                .whereEqualTo("userId", userId)
                .get()
                .await()

            for (document in favQuery.documents) {
                val documentId = document.id
                firestore.collection(FAVORITES).document(documentId).delete().await()
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun addDonutToCart(id: String, amount: Int) {
        val userId = dataStore.getUserId()
        try {
            val cartDocumentRef = firestore.collection(CART).document(userId)
            val donutDto = firestore.collection(DONUTS).document(id).get()
                .await().toObject<DonutDto>()
            val cart = cartDocumentRef.get().await()
            val cartItem = CartItemDto(
                itemId = id,
                amount = amount,
                price = amount * (donutDto?.price ?: 0.0),
                name = donutDto?.name,
                imageUrl = donutDto?.imageUrl
            )
            if (cart.data == null) {
                cartDocumentRef.set(
                    CartDto(
                        id = cartDocumentRef.id,
                        items = listOf(cartItem),
                        totalPrice = amount * (donutDto?.price ?: 0.0)
                    )
                ).await()
            } else {
                val existingCartDto =
                    cart.toObject(CartDto::class.java)
                existingCartDto.let { cartDto ->
                    val items = cartDto?.items?.toMutableList() ?: mutableListOf()
                    items.add(cartItem)
                    val totalPrice = items.map { it.price }.sumOf { price: Double? -> price ?: 0.0 }
                    cartDocumentRef.set(
                        cartDto?.copy(items = items, totalPrice = totalPrice) ?: CartDto()
                    ).await()
                }
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun getCartItems(): Cart {
        val userId = dataStore.getUserId()
        try {
            return firestore.collection(CART).document(userId).get().await().toObject<CartDto>()
                ?.toEntity() ?: throw NotFoundException()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun getAllFavorites(): List<Donut> {
        val userId = dataStore.getUserId()
        try {
            val favoriteDonutIds =
                firestore.collection(FAVORITES).get().await().asSequence()
                    .map { it.toObject<FavoriteDto>() }
                    .filter { it.userId == userId }.map { it.donutId }.toList()

            return firestore.collection(DONUTS).get().await().asSequence()
                .map { it.toObject<DonutDto>() }.filter { it.id in favoriteDonutIds }
                .map { it.toEntity() }.toList()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    override suspend fun getIfFavorite(id: String): Boolean {
        return id in getAllFavorites().map { it.id }
    }


    companion object {
        const val DONUTS = "donuts"
        const val FAVORITES = "favorites"
        const val CART = "cart"
    }
}