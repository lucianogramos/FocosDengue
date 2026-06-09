package com.focos_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.auth_screen.ClickHereLink
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PasswordTextField
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TEXT_MD
import com.focos_dengue.ui.util.XS

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    toSignUpScreen: () -> Unit,
    toForgotPasswordScreen: () -> Unit,
    toReportScreen: () -> Unit,
    state: LoginUIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: (() -> Unit) -> Unit
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(2 * MD)) {
        TitleText("Bem-vindo")

        SecondaryText(
            text = "Faça login para continuar", fontSize = TEXT_MD,
            marginTop = SM, marginBottom = LG
        )

        PrimaryTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "E-mail",
            placeholder = "email@exemplo.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(XS))

        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            hasMinChars = state.passwordRequirements.hasMinChars,
            hasUpperCase = state.passwordRequirements.hasUpperCase,
            hasLowerCase = state.passwordRequirements.hasLowerCase,
            hasNumber = state.passwordRequirements.hasNumber,
            hasSpecialChar = state.passwordRequirements.hasSpecialChar
        )

        Spacer(Modifier.height(LG))

        PrimaryButton(text = "Entrar", onClick = {
            onLogin { toReportScreen() }
        })

        Spacer(Modifier.height(XS))

        ClickHereLink("Ainda não tem uma conta? ", "navigation") { toSignUpScreen() }
        ClickHereLink("Esqueceu a senha? ", "redirect") { toForgotPasswordScreen() }
    }
}
