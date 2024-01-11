package org.haidy.support.ui.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.haidy.support.data.local.IDataStore
import org.haidy.support.data.repository.AuthRepositoryImp
import org.haidy.support.data.repository.ChatRepositoryImp
import org.haidy.support.data.repository.UserRepositoryImp
import org.haidy.support.domain.repository.IAuthRepository
import org.haidy.support.domain.repository.IChatRepository
import org.haidy.support.domain.repository.IUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

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