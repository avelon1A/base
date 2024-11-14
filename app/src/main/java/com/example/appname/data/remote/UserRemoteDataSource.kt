package com.example.appname.data.remote

import android.util.Log
import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.model.authWithToken
import io.ktor.client.*
import io.ktor.client.request.*
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException


class UserRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun fetchUserData(token: String): Result<authWithToken, NetworkError> {
        val  response = try {
            httpClient.get("https://dummyjson.com/auth/me") {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
        catch (e: UnresolvedAddressException) {
            return  Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return  Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val loginResponse = response.body<authWithToken>()
                Log.d("auth response","$loginResponse")
               Result.Success(loginResponse)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginRespose, NetworkError> {
        Log.d("login","$loginRequest")
        val  response = try {
            httpClient.post("https://dummyjson.com/auth/login") {
                headers {
                    contentType(ContentType.Application.Json)
                    setBody(loginRequest)
                }
            }
        }
        catch (e: UnresolvedAddressException) {
            return  Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return  Result.Error(NetworkError.SERIALIZATION)
        }
        println("Response status: ${response.status.value}")
        println("Response body: ${response.bodyAsText()}")
        return when (response.status.value) {
            in 200..299 -> {
                val loginResponse = response.body<LoginRespose>()
                Result.Success(loginResponse)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}