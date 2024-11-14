package com.example.appname.domain.usecase

import com.example.appname.domain.model.User
import com.example.appname.domain.repository.UserRepository
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result

class GetUserDataUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(token: String): Result<User, NetworkError> {
        return repository.getUserData(token)
    }
}