package com.example.flightbooking.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.flightbooking.R

@Composable
fun RegisterScreen(navigateToLoginScreen: () -> Unit = { }) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPasswordIcon by rememberSaveable { mutableStateOf(false) }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val (createAccountTitle, nameTitle, nameTextField, emailTitle, emailTextField, passwordTitle, passwordTextField, agreeToTermsText, termsOfServiceText, signUpButton, orDivider, signUpWithGoogle, alreadyHaveAccountText, signInHereText) = createRefs()
        Text(
            text = stringResource(R.string.create_an_account),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(createAccountTitle) {
                top.linkTo(parent.top, margin = 60.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

        Text(
            text = stringResource(R.string.name),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(nameTitle) {
                top.linkTo(createAccountTitle.bottom, margin = 30.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

        OutlinedTextField(
            value = name, onValueChange = { name = it }, placeholder = {
                Text(stringResource(R.string.john_doe))
            }, modifier = Modifier
                .padding(5.dp)
                .constrainAs(nameTextField) {
                    top.linkTo(nameTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

        Text(
            text = stringResource(R.string.email_address),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(emailTitle) {
                top.linkTo(nameTextField.bottom, margin = 15.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

        OutlinedTextField(
            value = email, onValueChange = { email = it }, placeholder = {
                Text("hello@example.com")
            }, modifier = Modifier
                .padding(5.dp)
                .constrainAs(emailTextField) {
                    top.linkTo(emailTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

        Text(
            text = stringResource(R.string.password), style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(passwordTitle) {
                top.linkTo(emailTextField.bottom, margin = 15.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            trailingIcon = {
                if (!showPasswordIcon)
                    Icon(
                        imageVector = Icons.Default.VisibilityOff, contentDescription = "",
                        modifier = Modifier.clickable(
                            onClick = {
                                showPasswordIcon = !showPasswordIcon
                            }
                        ))
                else
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            onClick = {
                                showPasswordIcon = !showPasswordIcon
                            }
                        ))

            },
            visualTransformation = if (!showPasswordIcon) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            placeholder = {
                Text(stringResource(R.string.password))
            }, modifier = Modifier
                .padding(5.dp)
                .constrainAs(passwordTextField) {
                    top.linkTo(passwordTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                })

        Text(
            stringResource(R.string.by_continuing_you_agree_to_our),
            color = Color.Gray,
            modifier = Modifier.constrainAs(agreeToTermsText) {
                top.linkTo(passwordTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 10.dp)
            })

        Text(
            stringResource(R.string.terms_of_service),
            modifier = Modifier.constrainAs(termsOfServiceText) {
                top.linkTo(passwordTextField.bottom, margin = 20.dp)
                start.linkTo(agreeToTermsText.end, margin = 5.dp)
            },
            color = Color(0xFFe60000)
        )

        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFe60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(signUpButton) {
                    top.linkTo(agreeToTermsText.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },

            ) {
            Text(stringResource(R.string.sign_up))
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(orDivider) {
                top.linkTo(signUpButton.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
            HorizontalDivider(modifier = Modifier.width(170.dp), thickness = 2.dp)
            Text("or", modifier = Modifier.padding(10.dp), color = Color.Black)
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

        Text(
            stringResource(R.string.already_have_an_account_sign),
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(alreadyHaveAccountText) {
                start.linkTo(parent.start, margin = 60.dp)
                bottom.linkTo(parent.bottom, margin = 15.dp)
            })

        Text(
            stringResource(R.string.sign_in_here),
            color = Color(0xFFe60000),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(signInHereText) {
                start.linkTo(alreadyHaveAccountText.end, margin = 5.dp)
                bottom.linkTo(parent.bottom,margin = 15.dp)
            }.clickable(onClick = {
                navigateToLoginScreen()
            })
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPrev() {
    RegisterScreen()
}