package com.example.flightbooking

sealed class LoginIntents {
    data class ValidateLogin(val email:String, val password:String): LoginIntents()
}