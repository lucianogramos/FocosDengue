package com.focos_dengue.ui.main_screen.account_screen

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

data class AccountUIState(
    val newEmailValue: String = "",
    val newPasswordValue: String = "",
    val oldEmailValue: String = "",
    val oldPasswordValue: String = "",
    val confirmationPasswordValue: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements()
)

class AccountViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(AccountUIState())
        private set

    fun updateOldEmail(email: String) {
        uiState = uiState.copy(oldEmailValue = email)
    }

    fun updateNewEmail(email: String) {
        uiState = uiState.copy(newEmailValue = email)
    }

    fun updateOldPassword(password: String) {
        uiState = uiState.copy(oldPasswordValue = password)
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

    fun onSave(redirectUrl: String, callback: (SaveResult) -> Unit) {
        val data = listOf(
            uiState.oldEmailValue,
            uiState.newEmailValue,
            uiState.oldPasswordValue,
            uiState.newPasswordValue,
            uiState.confirmationPasswordValue
        )

        if (data.all { it.isEmpty() }) {
            callback(SaveResult.Error("Nenhum dado foi alterado"))
            return
        }

        val newEmail = uiState.newEmailValue
        val newPassword = uiState.newPasswordValue

        if (uiState.passwordRequirements.isValid) {
            callback(SaveResult.Error("A sua nova senha não é válida. Verifique os requisitos"))
            return
        }

        if (newPassword != uiState.confirmationPasswordValue) {
            callback(SaveResult.Error("A sua nova senha não é igual a senha de confirmação"))
            return
        }

        viewModelScope.launch {
            if (newEmail.isNotEmpty())
                authRepository.updateEmail(
                    redirectUrl = redirectUrl,
                    newEmail = newEmail
                )

            if (newPassword.isNotEmpty())
                authRepository.updatePassword(
                    redirectUrl = redirectUrl,
                    newPassword = newPassword
                )
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
