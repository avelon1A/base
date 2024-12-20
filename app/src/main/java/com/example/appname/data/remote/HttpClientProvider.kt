package com.example.appname.data.remote


import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object HttpClientProvider {
    fun createHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true;
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }
}
