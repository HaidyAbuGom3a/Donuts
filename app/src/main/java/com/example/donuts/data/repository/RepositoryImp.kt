package com.example.donuts.data.repository

import com.example.donuts.data.local.DataSource
import com.example.donuts.domain.entities.DonutEntity
import com.example.donuts.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dataSource: DataSource
) : Repository {
    override fun getAllDonuts(): List<DonutEntity> =
        dataSource.getAllDonuts()


    override fun getAllOffers(): List<DonutEntity> =
        dataSource.getAllOffers()

}