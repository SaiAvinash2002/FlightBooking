package com.example.flightbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.flightbooking.ui.theme.FlightBookingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()  // Show splash screen first.
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
//        setTheme(R.style.Theme_FlightBooking)  // Change the theme to app or default theme.
        enableEdgeToEdge()
        setContent {
            FlightBookingTheme {
                Scaffold { innerPadding ->
                    val navController = rememberNavController()
                    NavGraph(navController)
                }
            }
        }
    }
}
