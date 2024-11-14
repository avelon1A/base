package com.example.appname.data.repository

import com.example.appname.data.store.UserPreferencesDataStore
import com.example.appname.domain.repository.LoacalStorage
import kotlinx.coroutines.flow.Flow

class LocalStroageImp(private val userPreferencesDataStore: UserPreferencesDataStore): LoacalStorage
{
    override suspend fun saveToken(token: String) {
        userPreferencesDataStore.saveToken(token)
    }

    override suspend fun getToken(): Flow<String?> {
     return  userPreferencesDataStore.tokenFlow
    }

    override suspend fun clearToken() {
        userPreferencesDataStore.clearToken()
    }
}