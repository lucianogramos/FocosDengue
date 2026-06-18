package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.repository.AuthRepository
import com.focos_dengue.domain.validation.EmailValidator
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator
import kotlinx.coroutines.launch

data class SignUpUIState(
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val message: String = ""
)

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())

    private var isSigningUp = false

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

    fun updateMessage(message: String) {
        uiState = uiState.copy(message = message)
    }

    fun onSignUp() {
        if (isSigningUp)
            return
        isSigningUp = true

        updateMessage("")

        val email = uiState.email
        val password = uiState.password

        val message = when {
            email.isBlank() -> "Digite um e-mail"
            password.isBlank() -> "Digite uma senha"
            !EmailValidator.validate(email) -> "E-mail inválido"
            uiState.confirmationPassword.isBlank() -> "Confirme sua senha"
            !uiState.passwordRequirements.isValid -> "Senha inválida. Verifique os requisitos"
            password != uiState.confirmationPassword -> "As senhas não coincidem"
            else -> null
        }

        if (message != null) {
            updateMessage(message)
            return
        }

        viewModelScope.launch {
            authRepository.signUp(email, password)
            updateMessage("Enviamos um e-mail para você confirmar sua conta. Verifique sua caixa de e-mails")
            isSigningUp = false
        }
    }
}

class SignUpViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(authRepository) as T
    }
}
