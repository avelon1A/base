package com.example.appname.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Bank(
    val cardExpire: String,
    val cardNumber: String,
    val cardType: String,
    val currency: String,
    val iban: String
)