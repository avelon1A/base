package com.example.appname.data.remote

import android.util.Log
import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.model.authWithToken
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result
import com.example.appname.uitl.mapHttpStatusToNetworkError
import com.example.appname.uitl.mapToNetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class UserRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun fetchUserData(token: String): Result<authWithToken, NetworkError> {
        val response = try {
            httpClient.get("https://dummyjson.com/auth/me") {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        } catch (e: Exception) {
            return Result.Error(mapToNetworkError(e))
        }
        return when (response.status.value) {
            in 200..299 -> {
                val loginResponse = response.body<authWithToken>()
                Log.d("auth response", "$loginResponse")
                Result.Success(loginResponse)
            }
            in 401..599 -> {
                Result.Error(mapHttpStatusToNetworkError(response.status.value))
            }
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginRespose, NetworkError> {
        Log.d("login", "$loginRequest")
        val response = try {
            httpClient.post("https://dummyjson.com/auth/login") {
                headers {
                    contentType(ContentType.Application.Json)
                    setBody(loginRequest)
                }
            }
        }  catch (e: Exception) {
            return Result.Error(mapToNetworkError(e))
        }
        return when (response.status.value) {
            in 200..299 -> {
                val loginResponse = response.body<LoginRespose>()
                Result.Success(loginResponse)
            }
            in 401..599 -> {
                Result.Error(mapHttpStatusToNetworkError(response.status.value))
            }
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}