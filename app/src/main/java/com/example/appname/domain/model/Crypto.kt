package com.example.appname.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val coin: String,
    val network: String,
    val wallet: String
)