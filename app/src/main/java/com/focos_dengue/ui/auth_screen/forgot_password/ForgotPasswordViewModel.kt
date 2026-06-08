package com.focos_dengue.ui.auth_screen.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator

data class ForgotPasswordUIState(
    val password: String = "",
    val code: String = "",
    val codeSent: Boolean = false,
    val passwordRequirements: PasswordRequirements = PasswordRequirements()
)

class ForgotPasswordViewModel : ViewModel() {
    var uiState by mutableStateOf(ForgotPasswordUIState())
        private set

    fun updatePassword(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun updateCode(code: String) {
        uiState = uiState.copy(code = code)
    }

    fun onSendCode() {
        // TODO: Implementar a lógica de envio do código
        uiState = uiState.copy(codeSent = true)
    }

    fun onVerifyCode() {
        // TODO: Implementar a lógica de verificação do código
    }
}