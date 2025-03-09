package com.example.combiner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.combiner.ui.screens.LogInScreen
import com.example.combiner.ui.screens.MainScreen
import com.example.combiner.ui.screens.SignUpScreen
import com.example.combiner.ui.theme.CombinerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CombinerTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") { LogInScreen(navController) }
                    composable("main") { MainScreen() }
                    composable("sign_up") { SignUpScreen(navController) }
                }
            }
        }
    }
}
