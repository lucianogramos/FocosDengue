package com.focos_dengue.ui.main_screen.account_screen

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.focos_dengue.ui.main_screen.VerticalScrollableContent
import com.focos_dengue.ui.navigation.ScreenName

@Composable
fun AccountScreen(toReportScreen: () -> Unit, viewModel: AccountViewModel = viewModel()) {
    val context = LocalContext.current

    VerticalScrollableContent(
        bottomBar = {
            AccountScreenBottomBar(toReportScreen)
        }
    ) { innerPadding, scrollState ->
        AccountScreenContent(
            modifier = Modifier.padding(innerPadding),
            scrollState = scrollState,
            state = viewModel.uiState,
            onOldEmailChange = viewModel::updateOldEmail,
            onNewEmailChange = viewModel::updateNewEmail,
            onOldPasswordChange = viewModel::updateOldPassword,
            onNewPasswordChange = viewModel::updateNewPassword,
            onConfirmationPasswordChange = viewModel::updateConfirmationPassword,
            onSave = {
                viewModel.onSave("focosdengue://${ScreenName.LOGIN.route}") { result ->
                    when (result) {
                        is SaveResult.Success ->
                            Toast.makeText(context, "Configurações Salvas", Toast.LENGTH_LONG).show()
                        is SaveResult.Error ->
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            onLogout = {
                viewModel.onLogout { text ->
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                }
                Toast.makeText(context, "Logout realizado", Toast.LENGTH_LONG).show()
            }
        )
    }
}
