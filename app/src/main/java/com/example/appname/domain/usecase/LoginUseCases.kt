package com.example.appname.domain.usecase

import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.repository.UserRepository
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result

class LoginUseCases(private val repository: UserRepository) {
    suspend fun login(loginRequest: LoginRequest): Result<LoginRespose, NetworkError> {
        return repository.login(loginRequest)
    }

}