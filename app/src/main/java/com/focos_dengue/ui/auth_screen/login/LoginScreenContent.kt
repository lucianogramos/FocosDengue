package com.focos_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.focos_dengue.data.remote.auth.loginUsuario
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
fun LoginScreenContent(modifier: Modifier = Modifier, toSignUpScreen: (() -> Unit)? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize().padding(2 * MD)) {
        TitleText("Bem-vindo", marginTop = XS)

        ParagraphText("Faça login para continuar", marginTop = SM, marginBottom = LG)

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
            visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(XL))

        PrimaryButton("Entrar")  {
            scope.launch {
                loginUsuario(email, password)
            }
        }

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
            LoginScreenContent()
        }
    }
}
