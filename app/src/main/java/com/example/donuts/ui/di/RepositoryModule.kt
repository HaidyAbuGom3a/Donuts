package com.example.donuts.ui.di

import com.example.donuts.data.local.DataSource
import com.example.donuts.data.repository.RepositoryImp
import com.example.donuts.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Singleton
    @Provides
    fun bindRepository(
        dataSource: DataSource,
    ): Repository {
        return RepositoryImp(dataSource)
    }
}