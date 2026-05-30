package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.focos_dengue.data.remote.auth.cadastrarUsuario
import com.focos_dengue.ui.auth_screen.AuthTextField
import com.focos_dengue.ui.auth_screen.ClickHereLink
import com.focos_dengue.ui.components.ParagraphText
import com.focos_dengue.ui.components.PrimaryButton
import com.focos_dengue.ui.components.TitleText
import com.focos_dengue.ui.utils.LG
import com.focos_dengue.ui.utils.MD
import com.focos_dengue.ui.utils.SM
import com.focos_dengue.ui.utils.XL
import com.focos_dengue.ui.utils.XS
import kotlinx.coroutines.launch

@Composable
fun SignUpScreenContent(modifier: Modifier = Modifier, toLoginScreen: (() -> Unit)? = null) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize().padding(2 * MD)) {
        TitleText(
            text = "Bem-vindo",
            marginTop = XS
        )

        ParagraphText(
            text = "Faça seu cadastro para poder denunciar locais com foco de dengue e falta de acessibilidade",
            marginTop = SM,
            marginBottom = XL
        )

        AuthTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nome"
        )

        Spacer(Modifier.height(LG))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@exemplo.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(LG))

        AuthTextField(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(XL))

        PrimaryButton("Cadastrar") {
            scope.launch {
                cadastrarUsuario(email, password)
            }
        }

        Spacer(Modifier.height(XS))

        ClickHereLink("Já tem uma conta? ", "navigation") { toLoginScreen?.invoke() }
    }
}

@Preview
@Composable
private fun SignUpScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUpScreenContent()
        }
    }
}
