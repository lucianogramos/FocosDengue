package com.foco_acessibilidade_dengue.ui.login

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

@Composable
fun LoginActivity(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
        .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Bem-vindo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Faça login para continuar",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthInput(
            value = name,
            onValueChange = { name = it },
            label = "Nome",
            iconId = R.drawable.username_icon
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthInput(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@exemplo.com",
            iconId = R.drawable.email_icon,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthInput(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            iconId = R.drawable.password_icon,
            visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

// Prévia para ver o design sem precisar rodar no emulador
@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginActivity()
        }
    }
}
