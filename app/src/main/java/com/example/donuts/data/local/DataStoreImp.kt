package com.example.donuts.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreImp @Inject constructor(context: Context) : IDataStore {

    companion object {
        private const val PREFERENCES_FILE_NAME = "donuts"
        private val IS_LOGGED = booleanPreferencesKey("is_active")
        private val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore
    override suspend fun saveUserId(userId: String) {
        prefDataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    override suspend fun getUserId(): String {
        return prefDataStore.data.map { preferences -> preferences[USER_ID] }.first() ?: ""
    }

    override suspend fun saveIfLoggedIn(isLogged: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[IS_LOGGED] = isLogged
        }
    }

    override suspend fun getIfLoggedIn(): Boolean {
        return prefDataStore.data.map { preferences -> preferences[IS_LOGGED] }.first() ?: false
    }


    override suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[IS_FIRST_TIME] = isFirstTime
        }
    }

    override suspend fun getIfFirstTimeUseApp(): Boolean {
        return prefDataStore.data.map { preferences -> preferences[IS_FIRST_TIME] }.first() ?: false
    }
}