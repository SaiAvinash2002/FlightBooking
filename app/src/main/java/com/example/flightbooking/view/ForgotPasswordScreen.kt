package com.example.flightbooking.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.flightbooking.R


@Composable
fun ForgotPasswordScreen(
    navigateToResetScreen: () -> Unit = {},
    navigateToRegisterScreen: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val (forgotPasswordTitle, forgotPasswordDescription, emailTitle, emailTextField, passwordResetBtn, createAccountTitle) = createRefs()

        Text(
            stringResource(R.string.forgot_password),
            modifier = Modifier.constrainAs(forgotPasswordTitle) {
                top.linkTo(parent.top, margin = 150.dp)
            },
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            stringResource(R.string.enter_your_email_address_to_get_the_password_reset_link),
            letterSpacing = 1.5.sp,
            fontSize = 17.sp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(forgotPasswordDescription) {
                top.linkTo(forgotPasswordTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = stringResource(R.string.email_address),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(emailTitle) {
                top.linkTo(forgotPasswordDescription.bottom, margin = 60.dp)
                start.linkTo(parent.start)
            })

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            placeholder = {
                Text("hello@example.com")
            },
            modifier = Modifier.constrainAs(emailTextField) {
                top.linkTo(emailTitle.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = {
                navigateToResetScreen()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(passwordResetBtn) {
                    top.linkTo(emailTextField.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }) {
            Text(stringResource(R.string.password_reset))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(createAccountTitle) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                }) {
            Text(
                text = stringResource(R.string.create_an_account),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFFe60000),
                modifier = Modifier.clickable(
                    onClick = {
                        navigateToRegisterScreen()
                    }
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ForgotPasswordScreenPrev() {
    ForgotPasswordScreen()
}