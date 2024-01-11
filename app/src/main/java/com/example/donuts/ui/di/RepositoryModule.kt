package com.example.donuts.ui.di

import com.example.donuts.data.local.IDataStore
import com.example.donuts.data.repository.AuthRepositoryImp
import com.example.donuts.data.repository.ChatRepositoryImp
import com.example.donuts.data.repository.DonutsRepositoryImp
import com.example.donuts.data.repository.UserRepositoryImp
import com.example.donuts.domain.repository.IAuthRepository
import com.example.donuts.domain.repository.IChatRepository
import com.example.donuts.domain.repository.IDonutsRepository
import com.example.donuts.domain.repository.IUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        dataStore: IDataStore,
        firestore: FirebaseFirestore
    ): IDonutsRepository {
        return DonutsRepositoryImp(dataStore, firestore)
    }

    @Singleton
    @Provides
    fun bindAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): IAuthRepository {
        return AuthRepositoryImp(firebaseAuth, firestore)
    }

    @Singleton
    @Provides
    fun bindUserRepository(
        firestore: FirebaseFirestore,
        dataStore: IDataStore
    ): IUserRepository {
        return UserRepositoryImp(firestore, dataStore)
    }

    @Singleton
    @Provides
    fun bindChatRepository(
        firestore: FirebaseFirestore,
        userRepo: IUserRepository
    ): IChatRepository {
        return ChatRepositoryImp(firestore, userRepo)
    }
}