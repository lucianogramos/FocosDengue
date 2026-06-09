package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(
    toLoginScreen: () -> Unit,
    toReportScreen: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SignUpTopbar()
        }
    ) { innerPadding ->
        SignUpScreenContent(
            modifier = Modifier.padding(innerPadding),
            toLoginScreen = toLoginScreen,
            toReportScreen = toReportScreen,
            state = viewModel.uiState,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onConfirmationPasswordChange = viewModel::onConfirmationPasswordChange,
            onSignUp = viewModel::onSignUp
        )
    }
}
