package com.focos_dengue.ui.auth_screen.signup

import androidx.compose.foundation.layout.Column
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
import com.focos_dengue.R
import com.focos_dengue.ui.components.PrimaryDivider
import com.focos_dengue.ui.utils.XS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopbar() {
    val colorScheme = MaterialTheme.colorScheme
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Cadastrar",
                    modifier = Modifier.padding(horizontal = XS),
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
                    modifier = Modifier.padding(XS)
                )
            }
        )

        PrimaryDivider()
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
