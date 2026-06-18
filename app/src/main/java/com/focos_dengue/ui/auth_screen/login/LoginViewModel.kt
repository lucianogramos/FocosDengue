package com.focos_dengue.ui.auth_screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.repository.AuthRepository
import com.focos_dengue.domain.validation.EmailValidator
import kotlinx.coroutines.launch

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val message: String = ""
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
        private set

    private var isLoggingIn = false
    private var isSendingPasswordResetEmail = false

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateMessage(message: String) {
        uiState = uiState.copy(message = message)
    }

    fun onLogin(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (isLoggingIn)
            return

        updateMessage("")

        val email = uiState.email
        val password = uiState.password

        val message = when {
            email.isBlank() -> "Digite um e-mail"
            password.isBlank() -> "Digite uma senha"
            !EmailValidator.validate(email) -> "E-mail inválido"
            else -> null
        }

        if (message != null) {
            updateMessage(message)
            return
        }

        isLoggingIn = true

        viewModelScope.launch {
            authRepository.signIn(uiState.email, uiState.password).fold(
                onSuccess = { onSuccess() },
                onFailure = { t -> onFailure(t.message ?: "Ocorreu um erro") }
            )
            isLoggingIn = false
        }
    }

    fun onForgotPassword(redirectUrl: String) {
        if (isSendingPasswordResetEmail)
            return

        if (uiState.email.isBlank()) {
            updateMessage("Digite um e-mail que você quer recuperar a senha")
            return
        }

        isSendingPasswordResetEmail = true

        viewModelScope.launch {
            authRepository.recoverPassword(uiState.email, redirectUrl).onSuccess {
                updateMessage("Um e-mail foi enviado para ${uiState.email}")
            }
            isSendingPasswordResetEmail = false
        }
    }
}

class LoginViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authRepository) as T
    }
}
