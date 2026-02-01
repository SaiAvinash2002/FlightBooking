package com.example.flightbooking

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightbooking.view.ForgotPasswordScreen
import com.example.flightbooking.view.LoginScreen
import com.example.flightbooking.view.RegisterScreen
import com.example.flightbooking.view.ResetPasswordScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = "loginScreen"){
        composable("loginScreen"){
            LoginScreen(
                navigateToRegisterScreen={
                    navController.navigate("registerScreen")
                },
                navigateToForgotPasswordScreen={
                    navController.navigate("forgotPasswordScreen")
                }
            )
        }
        composable("registerScreen"){
            RegisterScreen()
        }
        composable("forgotPasswordScreen"){
            ForgotPasswordScreen()
        }
        composable("resetPasswordScreen"){
            ResetPasswordScreen()
        }
    }
}