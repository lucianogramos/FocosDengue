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
import com.focos_dengue.ui.navigation.ScreenName

@Composable
fun ResetPasswordScreen(toLoginScreen: () -> Unit, viewModel: ResetPasswordViewModel = viewModel()) {
    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(state.message) {
        if (state.message.isEmpty())
            return@LaunchedEffect
        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
        viewModel.updateMessage("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ResetPasswordContent(
            modifier = Modifier.padding(innerPadding),
            toLoginScreen = toLoginScreen,
            state = state,
            onPasswordChange = viewModel::onPasswordChange,
            updatePassword = { viewModel.updatePassword("focosdengue://${ScreenName.LOGIN.route}") }
        )
    }
}