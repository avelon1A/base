package com.example.appname.uitl

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

fun mapToNetworkError(e: Exception): NetworkError {
    return when (e) {
        is HttpRequestTimeoutException -> NetworkError.REQUEST_TIMEOUT
        is UnresolvedAddressException -> NetworkError.NO_INTERNET
        is IOException -> NetworkError.NO_INTERNET
        is SerializationException -> NetworkError.SERIALIZATION
        else -> NetworkError.UNKNOWN
    }
}
fun mapHttpStatusToNetworkError(statusCode: Int): NetworkError {
    return when (statusCode) {
        401 -> NetworkError.UNAUTHORIZED
        409 -> NetworkError.CONFLICT
        408 -> NetworkError.REQUEST_TIMEOUT
        413 -> NetworkError.PAYLOAD_TOO_LARGE
        in 500..599 -> NetworkError.SERVER_ERROR
        else -> NetworkError.UNKNOWN
    }
}