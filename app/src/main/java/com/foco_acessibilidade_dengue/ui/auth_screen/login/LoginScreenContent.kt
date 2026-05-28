package com.foco_acessibilidade_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foco_acessibilidade_dengue.R
import com.foco_acessibilidade_dengue.ui.auth_screen.AuthInput
import com.foco_acessibilidade_dengue.ui.auth_screen.ClickHereLink

@Composable
fun LoginScreenContent(modifier: Modifier = Modifier, toSignUpScreen: (() -> Unit)? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(24.dp)) {
        Spacer(Modifier.height(4.dp))

        val colorScheme = MaterialTheme.colorScheme
        print(colorScheme)

        Text(
            text = "Bem-vindo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Faça login para continuar",
            fontSize = 16.sp,
            color = colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(32.dp))

        AuthInput(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@exemplo.com",
            iconId = R.drawable.email_icon,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(16.dp))

        AuthInput(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            iconId = R.drawable.password_icon,
            visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(32.dp))

        Button(
            {},
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(colorScheme.primary, colorScheme.onPrimary, colorScheme.secondary, colorScheme.tertiary)
        ) {
            Text("Entrar")
        }

        Spacer(Modifier.height(4.dp))

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
