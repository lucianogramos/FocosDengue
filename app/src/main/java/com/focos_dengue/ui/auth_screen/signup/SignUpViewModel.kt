package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focos_dengue.data.remote.auth.cadastrarUsuario
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator
import kotlinx.coroutines.launch

data class SignUpUIState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements()
)

class SignUpViewModel : ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())

    fun onNameChange(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun onConfirmationPasswordChange(password: String) {
        uiState = uiState.copy(confirmationPassword = password)
    }

    fun onSignUp(onSucess: () -> Unit): Result<Unit> {
        if (!uiState.passwordRequirements.isValid)
            return Result.failure(Exception("Senha inválida. Verifique os requisitos"))

        if (uiState.password != uiState.confirmationPassword)
            return Result.failure(Exception("As senhas não coincidem"))

        viewModelScope.launch {
            cadastrarUsuario(uiState.email, uiState.password).onSuccess {
                onSucess()
            }
        }

        return Result.success(Unit)
    }
}