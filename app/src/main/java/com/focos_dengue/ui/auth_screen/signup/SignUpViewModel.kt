package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focos_dengue.data.remote.auth.singUser
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator
import kotlinx.coroutines.launch

data class SignUpUIState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements()
)

class SignUpViewModel : ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun onSignUp(): SignUpResult {
        if (!uiState.passwordRequirements.isValid) {
            return SignUpResult.Error("Senha inválida. Verifique os requisitos")
        }

        viewModelScope.launch {
            singUser(uiState.email, uiState.password)
        }
        return SignUpResult.Success
    }
}