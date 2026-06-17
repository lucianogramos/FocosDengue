package com.focos_dengue.ui.main_screen.account_screen

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.focos_dengue.ui.main_screen.VerticalScrollableContent
import com.focos_dengue.ui.navigation.ScreenName

@Composable
fun AccountScreen(
    toLoginScreen: () -> Unit,
    toReportScreen: () -> Unit,
    viewModel: AccountViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = viewModel.uiState

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isEmpty())
            return@LaunchedEffect
        Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
        viewModel.updateErrorMessage("")
    }

    VerticalScrollableContent(
        bottomBar = {
            AccountScreenBottomBar(toReportScreen)
        }
    ) { innerPadding, scrollState ->
        AccountScreenContent(
            modifier = Modifier.padding(innerPadding),
            scrollState = scrollState,
            state = state,
            onUpdateNewEmail = viewModel::updateNewEmail,
            onUpdateNewPassword = viewModel::updateNewPassword,
            onUpdateConfirmationPassword = viewModel::updateConfirmationPassword,
            onChangeEmail = {
                viewModel.onChangeEmail("focosdengue://${ScreenName.LOGIN.route}") { result ->
                    when (result) {
                        is SaveResult.Success -> {
                            Toast.makeText(context, "E-mail salvo", Toast.LENGTH_LONG).show()
                            toLoginScreen()
                        }
                        is SaveResult.Error ->
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            onChangePassword = {
                viewModel.onChangePassword("focosdengue://${ScreenName.LOGIN.route}") { result ->
                    when (result) {
                        is SaveResult.Success -> {
                            Toast.makeText(context, "Senha salva", Toast.LENGTH_LONG).show()
                            toLoginScreen()
                        }
                        is SaveResult.Error ->
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            onLogout = {
                viewModel.onLogout(
                    onSuccess = {
                        Toast.makeText(context, "Logout realizado com sucesso", Toast.LENGTH_LONG).show()
                        toLoginScreen()
                    },
                    onFailure = {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                )
            }
        )
    }
}
