package com.example.donuts.ui.di

import android.content.Context
import com.example.donuts.data.local.DataStoreImp
import com.example.donuts.data.local.IDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): IDataStore {
        return DataStoreImp(context)
    }

}