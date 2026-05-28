package com.foco_acessibilidade_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpScreen(toLoginScreen: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            SignUpTopbar()
        }
    ) { innerPadding ->
        SignUpScreenContent(Modifier.padding(innerPadding), toLoginScreen = toLoginScreen)
    }
}
