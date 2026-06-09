package com.focos_dengue.ui.auth_screen.signup

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(
    toLoginScreen: () -> Unit,
    toReportScreen: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isEmpty())
            return@LaunchedEffect
        Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
        viewModel.updateErrorMessage("")
    }

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
            state = state,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onConfirmationPasswordChange = viewModel::onConfirmationPasswordChange,
            onSignUp = viewModel::onSignUp
        )
    }
}
