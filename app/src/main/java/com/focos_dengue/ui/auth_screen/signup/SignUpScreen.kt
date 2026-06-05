package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(toLoginScreen: () -> Unit, viewModel: SignUpViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SignUpTopbar()
        }
    ) { innerPadding ->
        SignUpScreenContent(
            modifier = Modifier.padding(innerPadding),
            toLoginScreen = toLoginScreen,
            state = viewModel.uiState,
            onNameChange = viewModel::updateName,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onSignUp = viewModel::onSignUp
        )
    }
}
