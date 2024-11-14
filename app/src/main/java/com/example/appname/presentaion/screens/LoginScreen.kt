package com.example.appname.presentaion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appname.domain.model.LoginRequest
import com.example.appname.presentaion.viewmodels.MainViewModel
import com.example.appname.uitl.Result
import io.ktor.websocket.Frame
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(viewModel: MainViewModel = getViewModel()) {
    val user by viewModel.userState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Button(onClick = { viewModel.login(LoginRequest(email, password)) }) {
         Text("Fetch User Data")
        }
        when (val state = user) {
            is Result.Error -> {
                Text("error: ${state.error}")
            }
            is Result.Success -> {
                Spacer(modifier = Modifier.height(16.dp))
                Frame.Text("User ID: ${state.data.email}")
                Text(text = "Name: ${state.data.firstName} ${state.data.lastName}")
                Text("Email: ${state.data.email}")
            }
            else -> {

            }
        }

    }
}
