package com.example.appname.presentaion.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appname.domain.model.User
import com.example.appname.domain.usecase.GetUserDataUseCase
import com.example.appname.domain.usecase.TokenUseCases
import com.example.appname.presentaion.navigation.Screens
import com.example.appname.presentaion.screens.LoginScreen
import com.example.appname.presentaion.screens.MainScreen
import com.example.appname.uitl.NetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.appname.uitl.Result
import com.example.appname.uitl.onError
import com.example.appname.uitl.onSuccess
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class InitialViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val tokenUseCases: TokenUseCases,
) : ViewModel() {
    private val _userState = MutableStateFlow<Result<User, NetworkError>?>(null)
    val userState: StateFlow<Result<User, NetworkError>?> get() = _userState

    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf<Screens>(Screens.LoginScreen)
        private set


    init {
        viewModelScope.launch {
            tokenUseCases.getToken().collect { token ->
                if (token != null) {
                    fetchUserData(token)
                }
                else{
                    splashCondition = false
                    startDestination = Screens.LoginScreen
                }
            }
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            val user = getUserDataUseCase(token)
            user.onSuccess {
                _userState.value = user
                splashCondition = false
                startDestination = Screens.MainScreen
            }
            user.onError {
                _userState.value = user
                splashCondition = false
                startDestination = Screens.LoginScreen
            }

        }
    }
}

