package com.example.flightbooking.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flightbooking.R
import com.example.flightbooking.viewmodel.LoginViewModel

//import com.joelkanyi.jcomposecountrycodepicker.component.rememberKomposeCountryCodePickerState

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit ={},
    navigateToRegisterScreen: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var passowrd by rememberSaveable { mutableStateOf("") }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    var isEmailLogin by rememberSaveable { mutableStateOf(false) }
    var passwordVisibilityIcon by rememberSaveable { mutableStateOf(true) }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
//    val state = rememberKomposeCountryCodePickerState()
    var list = listOf("Email", "Phone Number")

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        val (loginTitle, welcomeTitle, emailTitle, emailTextField, passwordTitle, passwordTextField, forgotPasswordTitle, keepmeSignInCheckbox, keepmeSignInText, tabLayout, loginButton) = createRefs()
        Text(
            "Login", style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(loginTitle) {
                top.linkTo(parent.top, margin = 60.dp)
                start.linkTo(parent.start)
            })

        Text(
            stringResource(R.string.welcome_back_to_the_app),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(welcomeTitle) {
                top.linkTo(loginTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            })

        // TABS FOR LOGIN WITH EMAIL (OR) PHONE NUMBER
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .constrainAs(tabLayout) {
                    top.linkTo(welcomeTitle.bottom)
                }) {
            list.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        isEmailLogin = !isEmailLogin
                    },
                ) {
                    Text(tabTitle, modifier = Modifier.padding(20.dp))
                }
            }
        }

        if (!isEmailLogin) {
            // Login with email id.
            Text(
                text = stringResource(R.string.email_address),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(emailTitle) {
                    top.linkTo(tabLayout.bottom, margin = 15.dp)
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
        } else {
            // Login with phone number.
            Text(
                stringResource(R.string.phone_number),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(emailTitle) {
                    top.linkTo(tabLayout.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                })

            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text(stringResource(R.string.phone_number)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                leadingIcon = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

//                        KomposeCountryCodePicker(
//
//                        )
//
//                        Spacer(modifier = Modifier.width(4.dp))
//                        Text("|")
//                        Spacer(modifier = Modifier.width(4.dp))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

        }


        Text(
            text = "Password",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(passwordTitle) {
                top.linkTo(emailTextField.bottom, margin = 15.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = stringResource(R.string.forgot_password),
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
                if (passwordVisibilityIcon)
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_password)
                    )
                else
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = stringResource(R.string.toggle_password)
                    )
            },
            value = passowrd, onValueChange = { passowrd = it },
            label = {
                Text(stringResource(R.string.password))
            },
            modifier = Modifier.constrainAs(passwordTextField) {
                top.linkTo(passwordTitle.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Row(modifier = Modifier.constrainAs(keepmeSignInCheckbox) {
            top.linkTo(passwordTextField.bottom, margin = 15.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.CheckBox,
                contentDescription = stringResource(R.string.keep_me_signed_in_check_in),
                tint = Color(0xFFE60000)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(stringResource(R.string.keep_me_signed_in))
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
            Text(stringResource(R.string.login))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPrev() {

     LoginScreen()
}