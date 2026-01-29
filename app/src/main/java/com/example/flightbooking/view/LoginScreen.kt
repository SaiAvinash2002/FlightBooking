package com.example.flightbooking.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flightbooking.viewmodel.LoginViewModel
import kotlin.math.log

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {
    var email by rememberSaveable { mutableStateOf("") }
    var passowrd by rememberSaveable { mutableStateOf("") }
//    val uiState by loginViewModel.uiState.collectAsState()

    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    TabRow(selectedTabIndex = selectedTabIndex) { }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        val (loginTitle, welcomeTitle, emailTitle, emailTextField, passwordTitle, passwordTextField, forgotPasswordTitle, keepmeSignInCheckbox, keepmeSignInText,loginButton) = createRefs()
        Text(
            "Login", style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(loginTitle) {
                top.linkTo(parent.top, margin = 60.dp)
                start.linkTo(parent.start)
            })

        Text(
            "Welcome back to the app", style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(loginTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = "Email Address",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(emailTitle) {
                top.linkTo(welcomeTitle.bottom, margin = 15.dp)
                start.linkTo(parent.start)
            })

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = {
                Text("hello@example.com")
            },
            modifier = Modifier.constrainAs(emailTextField) {
                top.linkTo(emailTitle.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(passwordTitle) {
                top.linkTo(emailTextField.bottom, margin = 15.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color(0xFFE60000),
            modifier = Modifier.constrainAs(forgotPasswordTitle) {
                top.linkTo(emailTextField.bottom, margin = 15.dp)
                end.linkTo(parent.end)
            })

        OutlinedTextField(
            trailingIcon = {
                Icon(imageVector = Icons.Default.Visibility, contentDescription = "toggle password")
            },
            value = email, onValueChange = { email = it },
            label = {
                Text("Password")
            },
            modifier = Modifier.constrainAs(passwordTextField) {
                top.linkTo(passwordTitle.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Row( modifier = Modifier.constrainAs(keepmeSignInCheckbox) {
            top.linkTo(passwordTextField.bottom, margin = 15.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CheckBox, contentDescription = "keep me signed in check in",
                tint = Color(0xFFE60000)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Keep me Signed In",)
        }


        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(loginButton) {
                    top.linkTo(keepmeSignInCheckbox.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Login")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}