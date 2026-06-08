package com.focos_dengue.ui.auth_screen.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focos_dengue.data.remote.auth.recoverPassword
import com.focos_dengue.data.remote.report.ReportRepository
import com.focos_dengue.domain.validation.PasswordRequirements
import com.focos_dengue.domain.validation.PasswordValidator
import kotlinx.coroutines.launch

data class ForgotPasswordUIState(
    val password: String = "",
    val passwordRequirements: PasswordRequirements = PasswordRequirements()
)

class ForgotPasswordViewModel : ViewModel() {
    var uiState by mutableStateOf(ForgotPasswordUIState())
        private set

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(
            password = password,
            passwordRequirements = PasswordValidator.validate(password)
        )
    }

    fun updatePassword() {
        viewModelScope.launch {
            // recoverPassword()
        }
    }
}