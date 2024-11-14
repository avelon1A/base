package com.example.appname.data.repository

import com.example.appname.data.remote.UserRemoteDataSource
import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.model.User
import com.example.appname.domain.repository.UserRepository
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result

class UserRepositoryImpl(private val remoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun getUserData(token: String): Result<User, NetworkError> {
        return remoteDataSource.fetchUserData(token)
    }

    override suspend fun login(loginRequest: LoginRequest ): Result<LoginRespose, NetworkError> {
        return remoteDataSource.login(loginRequest)
    }
}