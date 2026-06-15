package com.focos_dengue.ui.auth_screen.reset_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import com.focos_dengue.R
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PasswordTextField
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.PrimaryIconButton
import com.focos_dengue.ui.util.TitleText

@Composable
fun ResetPasswordContent(
    modifier: Modifier = Modifier,
    toLoginScreen: () -> Unit,
    state: ResetPasswordUIState,
    onPasswordChange: (String) -> Unit,
    updatePassword: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(2 * MD)) {
        PrimaryIconButton(
            iconId = R.drawable.arrow_back,
            contentDescription = "Voltar para a tela de Login",
            onClick = toLoginScreen
        )

        TitleText("Recuperação de Senha", marginTop = MD, marginBottom = LG)

        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "Nova Senha",
            placeholder = "Digite sua nova senha...",
            hasMinChars = state.passwordRequirements.hasMinChars,
            hasUpperCase = state.passwordRequirements.hasUpperCase,
            hasLowerCase = state.passwordRequirements.hasLowerCase,
            hasNumber = state.passwordRequirements.hasNumber,
            hasSpecialChar = state.passwordRequirements.hasSpecialChar
        )

        Spacer(Modifier.height(MD))

        PrimaryButton(text = "Alterar Senha", onClick = updatePassword)
    }
}
