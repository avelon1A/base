package com.example.appname.presentaion.navigation

import com.example.appname.presentaion.screens.LoginScreen
import com.example.appname.presentaion.screens.MainScreen
import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object LoginScreen : Screens()

    @Serializable
    data object MainScreen : Screens()
}