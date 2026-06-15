package com.focos_dengue.ui.auth_screen.reset_password

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

data class ResetPasswordUIState(
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val errorMessage: String = ""
)

class ResetPasswordViewModel(
    private val authRepository: AuthRepository,
    private val accessToken: String,
    private val refreshToken: String
) : ViewModel() {
    var uiState by mutableStateOf(ResetPasswordUIState())
        private set

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun updateErrorMessage(errorMessage: String) {
        uiState = uiState.copy(errorMessage = errorMessage)
    }

    fun updatePassword(redirectUrl: String) {
        val password = uiState.password

        if (password.isBlank()) {
            updateErrorMessage("Digite uma senha")
            return
        }

        if (uiState.passwordRequirements.isValid) {
            updateErrorMessage("Senha inválida. Verifique os requisitos")
            return
        }

        if (password != uiState.confirmationPassword) {
            updateErrorMessage("As senhas não coincidem")
            return
        }

        viewModelScope.launch {
            authRepository.updatePassword(
                accessToken = accessToken,
                refreshToken = refreshToken,
                redirectUrl = redirectUrl,
                newPassword = uiState.password
            )
        }
    }
}

class ResetPasswordViewModelFactory(
    private val authRepository: AuthRepository,
    private val accessToken: String,
    private val refreshToken: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResetPasswordViewModel(authRepository, accessToken, refreshToken) as T
    }
}
