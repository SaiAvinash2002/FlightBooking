package com.example.flightbooking

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            LoginScreenTesting()
        }
    }

    @Test
    fun test_email_fields() {
        composeRule.onNodeWithTag("emailTag")
            .performTextInput("sample@gmail.com")
            .equals("sample@gmail.com")
    }

    @Test
    fun test_password_fields() {
        composeRule.onNodeWithTag("passwordTag")
            .performTextInput("@123hello")
            .equals("@123hello")
    }

    @Test
    fun test_email_and_password_fields() {
        composeRule.onNodeWithTag("emailTag")
            .performTextInput("sample@gmail.com")
            .equals("sample@gmail.com")

        composeRule.onNodeWithTag("visibilityOffTag")
            .performClick()

        composeRule.onNodeWithTag("passwordTag")
            .performTextInput("@123hello")
            .equals("@123hello")
    }

    @Test
    fun test_checkbox_if_checked() {
        composeRule.onNodeWithTag("filledCheckBoxTag")
            .assertIsDisplayed()
    }

    @Test
    fun test_checkbox_if_unchecked() {
        composeRule.onNodeWithTag("filledCheckBoxTag")
            .performClick()

        composeRule.onNodeWithTag("unCheckedBoxTag")
            .assertIsDisplayed()
    }

    @Test
    fun test_create_an_account_is_clickable(){
        composeRule.onNodeWithTag("createAnAccountTag")
            .assertHasClickAction()
    }
}