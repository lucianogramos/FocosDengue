package com.focos_dengue.ui.auth_screen.reset_password

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ResetPasswordScreen(toLoginScreen: () -> Unit, viewModel: ResetPasswordViewModel = viewModel()) {
    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isEmpty())
            return@LaunchedEffect
        Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
        viewModel.updateErrorMessage("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ResetPasswordContent(
            modifier = Modifier.padding(innerPadding),
            toLoginScreen = toLoginScreen,
            state = state,
            onPasswordChange = viewModel::onPasswordChange,
            updatePassword = viewModel::updatePassword
        )
    }
}