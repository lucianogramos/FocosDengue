package com.focos_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    toSignUpScreen: () -> Unit,
    toForgotPasswordScreen: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LoginTopbar()
        }
    ) { innerPadding ->
        LoginScreenContent(
            modifier = Modifier.padding(innerPadding),
            toSignUpScreen = toSignUpScreen,
            toForgotPasswordScreen = toForgotPasswordScreen,
            state = viewModel.uiState,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onLogin = viewModel::onLogin
        )
    }
}
