package com.foco_acessibilidade_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
fun SignUpTopbar() {
    val colorScheme = MaterialTheme.colorScheme
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Cadastrar",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = colorScheme.onBackground,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorScheme.background,
                titleContentColor = colorScheme.onBackground
            ),
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "Cadastrar",
                    modifier = Modifier.padding(4.dp)
                )
            }
        )

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 4.dp),
            color = colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpTopbarPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUpTopbar()
        }
    }
}
