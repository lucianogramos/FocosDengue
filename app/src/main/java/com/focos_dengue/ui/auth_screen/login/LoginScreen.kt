package com.focos_dengue.ui.auth_screen.login

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
fun LoginScreen(
    toSignUpScreen: () -> Unit,
    toReportScreen: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(state.message) {
        if (state.message.isEmpty())
            return@LaunchedEffect
        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
        viewModel.updateMessage("")
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
            state = state,
            onEmailChange = viewModel::updateEmail,
            onPasswordChange = viewModel::updatePassword,
            onLogin = {
                viewModel.onLogin(
                    onSuccess = {
                        Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_LONG).show()
                        toReportScreen()
                    },
                    onFailure = {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                )
            },
            onForgotPassword = {
                viewModel.onForgotPassword("focosdengue://${ScreenName.RESET_PASSWORD.route}")
            }
        )
    }
}
