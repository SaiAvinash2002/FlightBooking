package com.example.flightbooking.view

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.flightbooking.R
import kotlinx.coroutines.delay

@SuppressLint("AutoboxingStateCreation")
@Composable
fun OTPVerificationScreen(navigateToHomeScreen: () -> Unit = {}) {
    var timerCount by rememberSaveable { mutableIntStateOf(25) }

    LaunchedEffect(Unit) {
        repeat(25) {
            delay(1000L)
            // OTP TIMER
            timerCount--
        }

    }
    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
        val (otpVerificationTitle, otpVerificationDescription, etField1, etField2, etField3, etField4, otpVerify, otpTimer, resendOtpButton, optTextField) = createRefs()

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

        OtpInputField(modifier = Modifier.constrainAs(optTextField) {
            top.linkTo(otpVerificationDescription.bottom, margin = 40.dp)
        })

        Button(
            onClick = {
                navigateToHomeScreen()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe60000)),
            modifier = Modifier
                .height(50.dp)
                .constrainAs(otpVerify) {
                    top.linkTo(optTextField.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }) {
            Text(stringResource(R.string.verify))
        }

        Text("Resend OTP in $timerCount",
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(otpTimer) {
            top.linkTo(otpVerify.bottom, margin = 20.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text(
            stringResource(R.string.resend_otp),
            modifier = Modifier.constrainAs(resendOtpButton) {
                top.linkTo(otpTimer.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun OtpInputField(modifier: Modifier) {
    val otpState = rememberTextFieldState()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            state = otpState,
            modifier = Modifier
                .size(300.dp, 56.dp)
                .semantics {
                    contentType = ContentType.SmsOtpCode
                },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            inputTransformation = InputTransformation.maxLength(4),
            lineLimits = TextFieldLineLimits.SingleLine,
            decorator = {
                val otp = otpState.text.toString()
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    repeat(4) { index ->
                        DigitDecor(
                            char = otp.getOrElse(index) { ' ' },
                            highlight = index == minOf(otp.length, 3)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun DigitDecor(char: Char, highlight: Boolean) {
    val borderSize by animateDpAsState(
        targetValue = if (highlight) 2.dp else 1.dp
    )

    val borderColor by animateColorAsState(
        targetValue = if (highlight) Color(0xFFe60000) else Color.Gray
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .border(borderSize, borderColor, RoundedCornerShape(10.dp))
    ) {
        Text(
            char.toString(),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OTPVerificationScreenPrev() {
    OTPVerificationScreen()
}