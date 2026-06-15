package com.focos_dengue.ui.auth_screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.repository.AuthRepository
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator
import kotlinx.coroutines.launch

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val message: String = ""
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
        private set

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun updateMessage(message: String) {
        uiState = uiState.copy(message = message)
    }

    fun onLogin(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        updateMessage("")

        val email = uiState.email
        val password = uiState.password

        val message = when {
            email.isBlank() -> "Digite um e-mail"
            password.isBlank() -> "Digite uma senha"
            !uiState.passwordRequirements.isValid -> "Senha inválida. Verifique os requisitos"
            else -> null
        }

        if (message != null) {
            updateMessage(message)
            return
        }

        viewModelScope.launch {
            authRepository.signIn(uiState.email, uiState.password).fold(
                onSuccess = { onSuccess() },
                onFailure = { t -> onFailure(t.message ?: "Ocorreu um erro") }
            )
        }
    }

    fun onForgotPassword(redirectUrl: String) {
        if (uiState.email.isBlank()) {
            updateMessage("Digite um e-mail que você quer recuperar a senha")
            return
        }

        viewModelScope.launch {
            authRepository.recoverPassword(uiState.email, redirectUrl).onSuccess {
                updateMessage("Um e-mail foi enviado para ${uiState.email}")
            }
        }
    }
}

class LoginViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authRepository) as T
    }
}
