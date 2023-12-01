package com.example.handlingbackenderrors.presentation.model

data class AuthState(
    val email : String = "",
    val password : String = "",
    val isDataVisible: Boolean = true,
    val isErrorVisible: Boolean = false
)