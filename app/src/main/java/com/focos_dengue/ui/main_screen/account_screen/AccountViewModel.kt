package com.focos_dengue.ui.main_screen.account_screen

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

data class AccountUIState(
    val newEmailValue: String = "",
    val newPasswordValue: String = "",
    val confirmationPasswordValue: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val errorMessage: String = ""
)

class AccountViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(AccountUIState())
        private set

    private var isSavingEmail = false
    private var isSavingPassword = false

    fun updateNewEmail(email: String) {
        uiState = uiState.copy(newEmailValue = email)
    }

    fun updateNewPassword(password: String) {
        uiState = uiState.copy(
            newPasswordValue = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun updateConfirmationPassword(password: String) {
        uiState = uiState.copy(confirmationPasswordValue = password)
    }

    fun updateErrorMessage(errorMessage: String) {
        uiState = uiState.copy(errorMessage = errorMessage)
    }

    fun onChangeEmail(redirectUrl: String, callback: (SaveResult) -> Unit) {
        if (isSavingEmail)
            return
        isSavingEmail = true

        val newEmail = uiState.newEmailValue

        val errorMessage = when {
            newEmail.isBlank() -> "Digite seu novo e-mail"
            EmailValidator.validate(newEmail) -> "Se novo e-mail é inválido"
            else -> null
        }

        if (errorMessage != null) {
            updateErrorMessage(errorMessage)
            return
        }

        viewModelScope.launch {
            if (newEmail.isNotEmpty()) {
                authRepository.updateEmail(
                    redirectUrl = redirectUrl,
                    newEmail = newEmail
                )
            }
            isSavingEmail = false
        }
        callback(SaveResult.Success)
    }

    fun onChangePassword(redirectUrl: String, callback: (SaveResult) -> Unit) {
        if (isSavingPassword)
            return
        isSavingPassword = true

        val newPassword = uiState.newPasswordValue

        val errorMessage = when {
            newPassword.isBlank() -> "Digite sua nova senha"
            !uiState.passwordRequirements.isValid -> "A sua nova senha não é válida. Verifique os requisitos"
            newPassword != uiState.confirmationPasswordValue -> "A sua nova senha não é igual a senha de confirmação"
            else -> null
        }

        if (errorMessage != null) {
            updateErrorMessage(errorMessage)
            return
        }

        viewModelScope.launch {
            if (newPassword.isNotEmpty()) {
                authRepository.updatePassword(
                    redirectUrl = redirectUrl,
                    newPassword = newPassword
                )
            }
            isSavingPassword = false
        }
        callback(SaveResult.Success)
    }

    fun onLogout(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            authRepository.logout().fold(
                onSuccess = {
                    onSuccess()
                },
                onFailure = {
                    onFailure(it.message ?: "Ocorreu um erro")
                }
            )
        }
    }
}

class AccountViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AccountViewModel(authRepository) as T
    }
}
