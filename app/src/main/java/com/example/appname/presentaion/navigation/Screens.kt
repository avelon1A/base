package com.example.appname.presentaion.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Screens {

    @Serializable
    data object LoginScreen : Screens()

    @Serializable
    data object MainScreen : Screens()

}