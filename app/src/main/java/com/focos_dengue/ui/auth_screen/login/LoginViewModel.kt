package com.focos_dengue.ui.auth_screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focos_dengue.data.remote.auth.loginUsuario
import kotlinx.coroutines.launch

data class LoginUIState(
    val email: String = "",
    val password: String = ""
)

class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
        private set

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun onLogin() {
        viewModelScope.launch {
            loginUsuario(uiState.email, uiState.password)
        }
    }
}