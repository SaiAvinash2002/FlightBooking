package com.example.flightbooking.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ResetPasswordScreen(
    navigateToLoginScreen: () -> Unit = {},
    navigateToRegisterScreen: () -> Unit = {}
) {
    var password by remember { mutableStateOf("") }
    var reEnterPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var reEnterShowPassword by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        val (resetPasswordTitle, resetPasswordDescription, enterPasswordtext, enterPasswordTextField, reenterPasswordtext, reenterPasswordTextField, resetButton, createAnAccount) = createRefs()

        // MISSING BACK BUTTON.
        Text(
            stringResource(R.string.reset_password),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(resetPasswordTitle) {
                top.linkTo(parent.top, margin = 150.dp)
            },
            fontWeight = FontWeight.Bold
        )

        Text(
            stringResource(R.string.enter_your_new_password_twice_below_to_reset_a_new_password),
            fontSize = 17.sp,
            letterSpacing = 2.sp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(resetPasswordDescription) {
                top.linkTo(resetPasswordTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Text(
            text = stringResource(R.string.enter_new_password),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(enterPasswordtext) {
                top.linkTo(resetPasswordDescription.bottom, margin = 50.dp)
                start.linkTo(parent.start)
            })

        OutlinedTextField(
            trailingIcon = {
                if (showPassword)
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = stringResource(R.string.toggle_password),
                        modifier = Modifier.clickable() {
                            showPassword = !showPassword
                        })
                else
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_password),
                        modifier = Modifier.clickable() {
                            showPassword = !showPassword
                        })

            },

            value = password, onValueChange = { password = it },
            placeholder = {
                Text(stringResource(R.string.password))
            },
            modifier = Modifier.constrainAs(enterPasswordTextField) {
                top.linkTo(enterPasswordtext.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            visualTransformation = if(showPassword){
                VisualTransformation.None
            }
            else{
                PasswordVisualTransformation()
            }
        )

        Text(
            text = stringResource(R.string.re_enter_new_password),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(reenterPasswordtext) {
                top.linkTo(enterPasswordTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start)
            })

        OutlinedTextField(
            trailingIcon = {
                if (reEnterShowPassword)
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "toggle password",
                        modifier = Modifier.clickable() {
                            reEnterShowPassword = !reEnterShowPassword
                        })
                else
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "toggle password",
                        modifier = Modifier.clickable() {
                            reEnterShowPassword = !reEnterShowPassword
                        })
            },
            visualTransformation = if(reEnterShowPassword){
                VisualTransformation.None
            }
            else{
                PasswordVisualTransformation()
            },
            value = reEnterPassword, onValueChange = { reEnterPassword = it },
            placeholder = {
                Text(stringResource(R.string.re_enter_password))
            },
            modifier = Modifier.constrainAs(reenterPasswordTextField) {
                top.linkTo(reenterPasswordtext.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = {
                if (password == reEnterPassword)
                    navigateToLoginScreen()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFe60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(resetButton) {
                    top.linkTo(reenterPasswordTextField.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(stringResource(R.string.reset_password))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createAnAccount) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }) {
            Text(
                stringResource(R.string.create_an_account),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFFe60000),
                modifier = Modifier.clickable(
                    onClick = { navigateToRegisterScreen() }
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ResetPasswordScreenPrev() {
    ResetPasswordScreen()
}