package com.example.appname.presentaion.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appname.domain.model.LoginRequest
import com.example.appname.domain.model.LoginRespose
import com.example.appname.domain.usecase.LoginUseCases
import com.example.appname.domain.usecase.TokenUseCases
import com.example.appname.uitl.onError
import com.example.appname.uitl.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import com.example.appname.uitl.NetworkError
import com.example.appname.uitl.Result

class LoginViewModel(private val loginUseCases: LoginUseCases,
                     private val tokenUseCases: TokenUseCases,):ViewModel() {

    private val _userState = MutableStateFlow<Result<LoginRespose, NetworkError>?>(null)
    val userState: StateFlow<Result<LoginRespose, NetworkError>?> get() = _userState



    fun login(loginRequest: LoginRequest) {
        Log.d("login", "Login Request: $loginRequest")
        viewModelScope.launch {
                val user = loginUseCases.login(loginRequest)
                user.onSuccess {
                    _userState.value = user
                    tokenUseCases.saveToken(it.accessToken)
                }
                user.onError {
                    _userState.value = user
                }

            }
        }
    }
