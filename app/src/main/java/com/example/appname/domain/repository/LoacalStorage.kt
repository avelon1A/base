package com.example.appname.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoacalStorage {
    suspend fun saveToken(token: String)
    suspend fun getToken(): Flow<String?>
    suspend fun clearToken()
}