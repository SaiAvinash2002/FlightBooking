package com.example.flightbooking.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val bottomNavMenu = listOf("Home", "Booking", "Offer", "Inbox", "Profile")
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Book Flight")
            }, actions = {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }, modifier = Modifier.testTag("homeScreenTitleTag"))
        },
        bottomBar = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                bottomNavMenu.forEach {

                }
            }
        },
        content = { it ->
            Text("")
        }
    )
}

data class BottomNavItem(
    val name: String,
    val icon: Icons,
    val isSelected: Boolean
)

@Composable
fun BottomNavigationBarItem(bottomNavItem: BottomNavItem) {
    Column() {
        Icons.Default.Home

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}