package com.example.flightbooking

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightbooking.view.ForgotPasswordScreen
import com.example.flightbooking.view.HomeScreen
import com.example.flightbooking.view.LoginScreen
import com.example.flightbooking.view.OTPVerificationScreen
import com.example.flightbooking.view.RegisterScreen
import com.example.flightbooking.view.ResetPasswordScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = "loginScreen") {
        composable("loginScreen") {
            LoginScreen(
                navigateToRegisterScreen = {
                    navController.navigate("registerScreen")
                },
                navigateToForgotPasswordScreen = {
                    navController.navigate("forgotPasswordScreen")
                },
                navigateToHomeScreen = {
                    navController.navigate("homeScreen")
                },
                navigateToOtpVerificationScreen = {
                    navController.navigate("otpVerificationScreen")
                }
            )
        }
        composable("registerScreen") {
            RegisterScreen(
                navigateToLoginScreen = { navController.navigate("loginScreen")},
            )
        }
        composable("forgotPasswordScreen") {
            ForgotPasswordScreen(
                navigateToResetScreen = { navController.navigate("resetPasswordScreen") },
                navigateToRegisterScreen = { navController.navigate("registerScreen") }
            )
        }
        composable("resetPasswordScreen") {
            ResetPasswordScreen(
                navigateToLoginScreen = { navController.navigate("loginScreen")},
                navigateToRegisterScreen = {  navController.navigate("registerScreen") }
            )
        }
        composable("otpVerificationScreen") {
            OTPVerificationScreen(
                navigateToHomeScreen = {
                    navController.navigate("homeScreen")
                }
            )
        }
        composable("homeScreen") {
            HomeScreen()
        }
    }
}