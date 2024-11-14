package com.example.appname.domain.usecase

import com.example.appname.domain.repository.LoacalStorage
import kotlinx.coroutines.flow.Flow

class TokenUseCases(private val localStorage: LoacalStorage) {
    suspend fun getToken(): Flow<String?> {
        return localStorage.getToken()
    }
    suspend fun saveToken(token: String) {
        localStorage.saveToken(token)
    }
    suspend fun clearToken() {
        localStorage.clearToken()

    }
}