package com.foco_acessibilidade_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SignUpScreenContent(modifier: Modifier = Modifier, toLoginScreen: (() -> Unit)? = null) {
    var name by remember { mutableStateOf("") }
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
            text = "Faça seu cadastro para poder denunciar locais com foco de dengue e falta de acessibilidade",
            fontSize = 16.sp,
            color = colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(32.dp))

        AuthInput(
            value = name,
            onValueChange = { name = it },
            label = "Nome",
            iconId = R.drawable.username_icon
        )

        Spacer(Modifier.height(16.dp))

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
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.height(32.dp))

        Button(
            {},
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(colorScheme.primary, colorScheme.onPrimary, colorScheme.secondary, colorScheme.tertiary)
        ) {
            Text("Cadastrar")
        }

        Spacer(Modifier.height(4.dp))

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
