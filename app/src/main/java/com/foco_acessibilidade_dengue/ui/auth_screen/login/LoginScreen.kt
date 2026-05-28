package com.foco_acessibilidade_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(toSignUpScreen: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LoginTopbar()
        }
    ) { innerPadding ->
        LoginScreenContent(modifier = Modifier.padding(innerPadding), toSignUpScreen = toSignUpScreen)
    }
}
