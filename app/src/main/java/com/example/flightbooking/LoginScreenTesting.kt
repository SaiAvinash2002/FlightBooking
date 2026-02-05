package com.example.flightbooking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun LoginScreenTesting() {
    var email by rememberSaveable { mutableStateOf("") }
    var passowrd by rememberSaveable { mutableStateOf("") }
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    var isEmailLogin by rememberSaveable { mutableStateOf(true) }
    var passwordVisibilityIcon by rememberSaveable { mutableStateOf(true) }
    var keepMeSignedIn by rememberSaveable { mutableStateOf(true) }
    val phoneNumber = rememberSaveable { mutableStateOf("") }

//    val state = rememberKomposeCountryCodePickerState()
    val list = listOf("Email", "Phone Number")

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        val (loginTitle, welcomeTitle, emailTitle, emailTextField, passwordTitle, passwordTextField, forgotPasswordTitle, keepmeSignInCheckbox, keepmeSignInText, tabLayout, loginButton, testColumn, phoneNumberLoginTitle, orDivider, signUpWithGoogle, createAnAccountBtn) = createRefs()
        Text(
            stringResource(R.string.login), style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(loginTitle) {
                top.linkTo(parent.top, margin = 120.dp)
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
            contentColor = Color.Black,
            indicator = { tabsList ->
                if (selectedTabIndex < tabsList.size){
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabsList[selectedTabIndex]),
                        color = Color(0xFFe60000)
                    )
                }
            },
            modifier = Modifier
                .constrainAs(tabLayout) {
                    top.linkTo(welcomeTitle.bottom)
                }) {
            list.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        isEmailLogin = index == 0
                    },
                ) {
                    Text(tabTitle, modifier = Modifier.padding(20.dp), color = if(selectedTabIndex==index) Color(0xFFE60000) else Color.Black)
                }
            }
        }


        Column(modifier = Modifier.constrainAs(testColumn) {
            top.linkTo(tabLayout.bottom)
        }) {

            Spacer(modifier = Modifier.height(15.dp))
            if (isEmailLogin) {
                // Login with email id.
                Text(
                    text = stringResource(R.string.email_address),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    placeholder = {
                        Text("hello@example.com")
                    },
                    modifier = Modifier.fillMaxWidth().testTag("emailTag")
                )
            } else {
                // Login with phone number.
                Text(
                    stringResource(R.string.phone_number),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    placeholder = { Text(stringResource(R.string.phone_number)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    leadingIcon = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                        KomposeCountryCodePicker()
//                        Spacer(modifier = Modifier.width(4.dp))
//                        Text("|")
//                        Spacer(modifier = Modifier.width(4.dp))

                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


        Text(
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(passwordTitle) {
                top.linkTo(testColumn.bottom, margin = 15.dp)
                start.linkTo(parent.start)
            })

        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color(0xFFE60000),
            modifier = Modifier
                .constrainAs(forgotPasswordTitle) {
                    top.linkTo(testColumn.bottom, margin = 15.dp)
                    end.linkTo(parent.end)
                }
                .clickable(onClick = {
//                    navigateToForgotPasswordScreen()
                })
        )

        OutlinedTextField(
            trailingIcon = {
                if (passwordVisibilityIcon)
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_password),
                        modifier = Modifier.clickable(onClick = {
                            passwordVisibilityIcon = !passwordVisibilityIcon
                        }).testTag("visibilityOffTag")
                    )
                else
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = stringResource(R.string.toggle_password),
                        modifier = Modifier.clickable(onClick = {
                            passwordVisibilityIcon = !passwordVisibilityIcon
                        })
                    )
            },
            value = passowrd, onValueChange = { passowrd = it },
            placeholder = {
                Text(stringResource(R.string.password))
            },
            modifier = Modifier.constrainAs(passwordTextField) {
                top.linkTo(passwordTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.testTag("passwordTag"),

            // To make the password invisible.
            visualTransformation = if (!passwordVisibilityIcon) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }

        )

        Row(modifier = Modifier.constrainAs(keepmeSignInCheckbox) {
            top.linkTo(passwordTextField.bottom, margin = 15.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            // Toggle the icon of keep me signed in.
            if (keepMeSignedIn) {
                Icon(
                    imageVector = Icons.Default.CheckBox,
                    contentDescription = stringResource(R.string.keep_me_signed_in_check_in),
                    tint = Color(0xFFE60000),
                    modifier = Modifier.clickable(onClick = {
                        keepMeSignedIn = !keepMeSignedIn
                    }).testTag("filledCheckBoxTag")
                )
            } else {
                Icon(
                    imageVector = Icons.Default.CheckBoxOutlineBlank,
                    contentDescription = stringResource(R.string.keep_me_signed_in_check_in),
                    tint = Color(0xFFE60000),
                    modifier = Modifier.clickable(onClick = {
                        keepMeSignedIn = !keepMeSignedIn
                    }).testTag("unCheckedBoxTag")
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(stringResource(R.string.keep_me_signed_in))
        }


        Button(
            onClick = {
//                navigateToHomeScreen()
            },
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(orDivider) {
                top.linkTo(loginButton.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
            HorizontalDivider(modifier = Modifier.width(130.dp), thickness = 2.dp)
            Text(stringResource(R.string.or_sign_in_with), modifier = Modifier.padding(10.dp), color = Color.Black)
            HorizontalDivider(modifier = Modifier.width(180.dp), thickness = 2.dp)
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCBC4C4)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(signUpWithGoogle) {
                    top.linkTo(orDivider.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(stringResource(R.string.continue_with_google), color = Color.Black)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createAnAccountBtn) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }) {
            Text(
                stringResource(R.string.create_an_account),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFFe60000),
                modifier = Modifier.clickable(onClick = {
                }).testTag("createAnAccountTag")
            )
        }
    }
}