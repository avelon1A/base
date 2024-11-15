package com.example.appname.presentaion.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appname.domain.model.authWithToken
import com.example.appname.domain.usecase.GetUserDataUseCase
import com.example.appname.domain.usecase.TokenUseCases
import com.example.appname.presentaion.navigation.Screens
import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result
import com.example.appname.uitl.onError
import com.example.appname.uitl.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class InitialViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val tokenUseCases: TokenUseCases,
) : ViewModel() {
    private val _userState = MutableStateFlow<Result<authWithToken, NetworkError>?>(null)
    val userState: StateFlow<Result<authWithToken, NetworkError>?> get() = _userState

    var splashCondition by mutableStateOf(true)
        private set

     var _startDestination by mutableStateOf<Screens?>(null)
        private set


    init {
        viewModelScope.launch {

            tokenUseCases.getToken().collect { token ->
                if (token != null) {
                    fetchUserData(token)
                }
                else{
                    _startDestination = Screens.LoginScreen
                    splashCondition = false
                }
            }
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            val user = getUserDataUseCase(token)
            user.onSuccess {
                _userState.value = user
                if(_startDestination == null){
                    _startDestination = Screens.MainScreen
                }
                splashCondition = false
            }
            user.onError {
                _userState.value = user
                _startDestination = Screens.LoginScreen
                splashCondition = false
            }


        }
    }
}

