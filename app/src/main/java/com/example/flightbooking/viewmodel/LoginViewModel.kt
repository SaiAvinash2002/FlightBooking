package com.example.flightbooking.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flightbooking.LoginIntents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.math.log

//@HiltViewModel
class LoginViewModel: ViewModel() {

    fun intentDispatcher(loginIntents: LoginIntents){
        when(loginIntents) {
            is LoginIntents.ValidateLogin -> {

            }
        }
    }
}