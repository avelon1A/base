package com.example.appname.presentaion.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.appname.presentaion.navigation.AppNavHost
import com.example.appname.presentaion.screens.MainScreen
import com.example.appname.presentaion.theme.AppTheme
import com.example.appname.presentaion.viewmodels.InitialViewModel
import com.microsoft.clarity.Clarity
import com.microsoft.clarity.ClarityConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: InitialViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = ClarityConfig("p1i0y081y9")
        Clarity.initialize(applicationContext, config)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel._startDestination == null
            }
        }
        setContent {
            AppTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val navController = rememberNavController()
                    if(viewModel._startDestination != null){
                        AppNavHost(
                            navController = navController,
                            modifier = Modifier.padding(),
                            startDestination = viewModel._startDestination!!
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainScreen()
    }
}

