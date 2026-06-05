package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.focos_dengue.data.remote.auth.cadastrarUsuario
import kotlinx.coroutines.launch

data class SignUpUIState(
    val name: String = "",
    val email: String = "",
    val password: String = ""
)

class SignUpViewModel : ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun onSignUp() {
        viewModelScope.launch {
            cadastrarUsuario(uiState.email, uiState.password)
        }
    }
}