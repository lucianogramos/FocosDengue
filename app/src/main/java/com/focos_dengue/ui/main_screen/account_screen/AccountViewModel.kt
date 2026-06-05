package com.focos_dengue.ui.main_screen.account_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class AccountUIState(
    val newName: String = "",
    val newEmailValue: String = "",
    val newPasswordValue: String = "",
    val oldEmailValue: String = "",
    val oldPasswordValue: String = "",
    val confirmationPasswordValue: String = ""
)

class AccountViewModel : ViewModel() {
    var uiState by mutableStateOf(AccountUIState())
        private set

    fun updateNewName(name: String) {
        uiState = uiState.copy(newName = name)
    }

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
        uiState = uiState.copy(newPasswordValue = password)
    }

    fun updateConfirmationPassword(password: String) {
        uiState = uiState.copy(confirmationPasswordValue = password)
    }

    fun onSave(onShowToast: (String) -> Unit) {
        val data = listOf(
            uiState.oldEmailValue,
            uiState.newEmailValue,
            uiState.oldPasswordValue,
            uiState.newPasswordValue,
            uiState.confirmationPasswordValue
        )

        if (data.all { it.isEmpty() }) {
            onShowToast("Nenhum dado foi alterado")
            return
        }

        if (uiState.newPasswordValue != uiState.confirmationPasswordValue) {
            onShowToast("A sua nova senha não é igual a senha de confirmação")
            return
        }

        // TODO: Implementar a lógica de salvamento real (ex: chamada ao repositório)
        onShowToast("Configurações salvas")
    }
}
