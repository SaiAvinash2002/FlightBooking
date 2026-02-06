package com.example.flightbooking

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.assertTextEquals

class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test_email_fields() {
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("emailTag")
            .performTextInput("sample@gmail.com")
        composeRule.onNodeWithTag("emailTag").assertTextEquals("sample@gmail.com")
    }

    @Test
    fun test_password_fields() {
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("passwordTag")
            .performTextInput("@123hello")
        composeRule.onNodeWithTag("passwordTag").assertTextEquals("@123hello")
    }

    @Test
    fun test_email_and_password_fields() {
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("emailTag")
            .performTextInput("sample@gmail.com")
        composeRule.onNodeWithTag("emailTag").assertTextEquals("sample@gmail.com")

        composeRule.onNodeWithTag("visibilityOffTag")
            .performClick()

        composeRule.onNodeWithTag("passwordTag")
            .performTextInput("@123hello")
        composeRule.onNodeWithTag("passwordTag").assertTextEquals("@123hello")
    }

    @Test
    fun test_checkbox_if_checked() {
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("filledCheckBoxTag")
            .assertIsDisplayed()
    }

    @Test
    fun test_checkbox_if_unchecked() {
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("filledCheckBoxTag")
            .performClick()

        composeRule.onNodeWithTag("unCheckedBoxTag")
            .assertIsDisplayed()
    }

    @Test
    fun test_create_an_account_is_clickable(){
        composeRule.setContent {
            LoginScreenTesting()
        }
        composeRule.onNodeWithTag("createAnAccountTag")
            .assertHasClickAction()
    }

    @Test
    fun test_login_button_navigating_to_home_screen(){

        composeRule.setContent {
            val context = LocalContext.current
            val navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(navController = navController)
        }

        composeRule.onNodeWithTag("emailTag")
            .performTextInput("sample@gmail.com")

        composeRule.onNodeWithTag("passwordTag")
            .performTextInput("@123hello")

        composeRule.onNodeWithTag("loginButtonTag")
            .performClick()

        composeRule.onNodeWithTag("homeScreenTitleTag")
            .assertIsDisplayed()
    }
}