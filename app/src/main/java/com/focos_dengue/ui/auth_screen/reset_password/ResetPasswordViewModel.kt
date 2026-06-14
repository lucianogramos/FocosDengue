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

data class ForgotPasswordUIState(
    val password: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val errorMessage: String = ""
)

class ForgotPasswordViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(ForgotPasswordUIState())
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

    fun updatePassword() {
        viewModelScope.launch {
            authRepository.updatePassword(uiState.password)
        }
    }
}

class ResetPasswordViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForgotPasswordViewModel(authRepository) as T
    }
}
