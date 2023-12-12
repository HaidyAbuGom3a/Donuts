package com.example.donuts.data.repository

import android.content.res.Resources
import com.example.donuts.data.dto.DonutDto
import com.example.donuts.data.fake.IDataSource
import com.example.donuts.data.mapper.toEntity
import com.example.donuts.domain.entities.Donut
import com.example.donuts.domain.repository.IDonutsRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class DonutsRepositoryImp @Inject constructor(
    private val dataSource: IDataSource,
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
                .await().toObject<DonutDto>()?.toEntity() ?: throw Resources.NotFoundException()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }


    companion object {
        const val DONUTS = "donuts"
    }
}