package com.focos_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.auth_screen.ClickHereLink
import com.focos_dengue.ui.util.ParagraphText
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.XL
import com.focos_dengue.ui.util.XS

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    toSignUpScreen: (() -> Unit)? = null,
    state: LoginUIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(2 * MD)) {
        TitleText("Bem-vindo", marginTop = XS)

        ParagraphText("Faça login para continuar", marginTop = SM, marginBottom = LG)

        PrimaryTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "E-mail",
            placeholder = "email@exemplo.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(LG))

        PrimaryTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "Senha",
            visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(XL))

        PrimaryButton(text = "Entrar", onClick = onLogin)

        Spacer(Modifier.height(XS))

        ClickHereLink("Ainda não tem uma conta? ", "navigation") { toSignUpScreen?.invoke() }
        ClickHereLink("Esqueceu a senha? ", "redirect") {}
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                state = LoginUIState(),
                onEmailChange = {},
                onPasswordChange = {},
                onLogin = {}
            )
        }
    }
}
