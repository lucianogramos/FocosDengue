package com.foco_acessibilidade_dengue.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foco_acessibilidade_dengue.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopbar() {
    TopAppBar(
        title = { Text("Entrar", modifier = Modifier.padding(horizontal = 4.dp)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = "Entrar"
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginTopbarPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginTopbar()
        }
    }
}