package com.example.flightbooking.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

@Composable
fun OTPVerificationScreen() {
    var timerCount by rememberSaveable { mutableStateOf(25) }

    LaunchedEffect(Unit) {
        repeat(25) {
            delay(1000L)
            // OTP TIMER
        }

    }
    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
        val (otpVerificationTitle, otpVerificationDescription, etField1, etField2, etField3, etField4, otpVerify, otpTimer, resendOtpButton) = createRefs()

        Text(
            stringResource(R.string.otp_verification),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(otpVerificationTitle) {
                top.linkTo(parent.top, margin = 150.dp)
            },
            fontWeight = FontWeight.Bold
        )

        Text(
            stringResource(R.string.enter_the_verification_code_we_just_sent_on_your_phone_number),
            letterSpacing = 1.5.sp,
            fontSize = 17.sp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(otpVerificationDescription) {
                top.linkTo(otpVerificationTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(otpVerify) {
                    top.linkTo(otpVerificationDescription.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }) {
            Text(stringResource(R.string.verify))
        }

        Text(
            "Resend OTP",
            modifier = Modifier.constrainAs(resendOtpButton) {
                top.linkTo(otpVerify.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )

    }
}


@Preview(showSystemUi = true)
@Composable
private fun OTPVerificationScreenPrev() {
    OTPVerificationScreen()
}