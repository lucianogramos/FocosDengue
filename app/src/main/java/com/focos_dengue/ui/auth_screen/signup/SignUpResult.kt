package com.focos_dengue.ui.auth_screen.signup

sealed class SignUpResult {
    object Success : SignUpResult()
    data class Error(val message: String) : SignUpResult()
}