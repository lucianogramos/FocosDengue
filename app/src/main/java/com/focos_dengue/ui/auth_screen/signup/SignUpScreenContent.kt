package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    toLoginScreen: (() -> Unit)? = null,
    state: SignUpUIState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(2 * MD)) {
        TitleText(
            text = "Bem-vindo",
            marginTop = XS
        )

        ParagraphText(
            text = "Faça seu cadastro para poder denunciar locais com foco de dengue e falta de acessibilidade",
            marginTop = SM,
            marginBottom = XL
        )

        PrimaryTextField(
            value = state.name,
            onValueChange = onNameChange,
            label = "Nome"
        )

        Spacer(Modifier.height(LG))

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
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(XL))

        PrimaryButton(text = "Cadastrar", onClick = onSignUp)

        Spacer(Modifier.height(XS))

        ClickHereLink("Já tem uma conta? ", "navigation") { toLoginScreen?.invoke() }
    }
}

@Preview
@Composable
private fun SignUpScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUpScreenContent(
                state = SignUpUIState(),
                onNameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onSignUp = {}
            )
        }
    }
}
