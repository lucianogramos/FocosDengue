package com.focos_dengue.ui.auth_screen.forgot_password

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForgotPasswordScreen(toLoginScreen: () -> Unit, viewModel: ForgotPasswordViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ForgotPasswordContent(
            modifier = Modifier.padding(innerPadding),
            toLoginScreen = toLoginScreen,
            state = viewModel.uiState,
            onPasswordChange = viewModel::onPasswordChange,
            onUpdatePassword = viewModel::updatePassword
        )
    }
}