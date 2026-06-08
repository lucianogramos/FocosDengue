package com.focos_dengue.ui.auth_screen.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ForgotPasswordUIState(
    val password: String = "",
    val code: String = "",
    val codeSent: Boolean = false
)

class ForgotPasswordViewModel : ViewModel() {
    var uiState by mutableStateOf(ForgotPasswordUIState())
        private set

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }
    fun updatePassword() {

    }
}