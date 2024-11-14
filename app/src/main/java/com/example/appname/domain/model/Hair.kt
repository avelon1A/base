package com.example.appname.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Hair(
    val color: String,
    val type: String
)