package com.example.appname.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRespose(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)
