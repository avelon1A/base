package com.example.appname.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val address: Address,
    val department: String,
    val name: String,
    val title: String
)