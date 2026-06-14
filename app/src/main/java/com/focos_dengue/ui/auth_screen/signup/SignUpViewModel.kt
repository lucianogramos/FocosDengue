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
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val errorMessage: String = ""
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

    fun updateErrorMessage(errorMessage: String) {
        uiState = uiState.copy(errorMessage = errorMessage)
    }

    fun onSignUp(onSucess: () -> Unit) {
        updateErrorMessage("")

        val name = uiState.name
        val email = uiState.email
        val password = uiState.password

        val errorMessage = when {
            name.isBlank() -> "Digite um nome"
            email.isBlank() -> "Digite um e-mail"
            password.isBlank() -> "Digite uma senha"
            uiState.confirmationPassword.isBlank() -> "Confirme sua senha"
            !uiState.passwordRequirements.isValid -> "Senha inválida. Verifique os requisitos"
            password != uiState.confirmationPassword -> "As senhas não coincidem"
            else -> null
        }

        if (errorMessage != null) {
            updateErrorMessage(errorMessage)
            return
        }

        viewModelScope.launch {
            cadastrarUsuario(email, password).onSuccess {
                onSucess()
            }
        }
    }
}