package com.example.appname.domain.repository

import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.model.authWithToken
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result

interface UserRepository {
    suspend fun getUserData(token: String): Result<authWithToken, NetworkError>
    suspend fun login(loginRequest: LoginRequest): Result<LoginRespose, NetworkError>
}