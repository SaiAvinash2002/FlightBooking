package com.example.flightbooking

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flightbooking.view.LoginScreen
import com.example.flightbooking.view.RegisterScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "loginScreen"){
        composable("loginScreen"){
            LoginScreen()
        }
        composable("registerScreen"){
            RegisterScreen()
        }
    }
}