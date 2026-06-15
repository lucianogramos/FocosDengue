package com.focos_dengue.ui.auth_screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    toSignUpScreen: () -> Unit,
    toReportScreen: () -> Unit,
    viewModel: LoginViewModel = viewModel()
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
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LoginTopbar()
        }
    ) { innerPadding ->
        LoginScreenContent(
            modifier = Modifier.padding(innerPadding),
            toSignUpScreen = toSignUpScreen,
            toReportScreen = toReportScreen,
            state = state,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onLogin = viewModel::onLogin,
            onForgotPassword = viewModel::onForgotPassword
        )
    }
}
