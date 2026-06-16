package com.focos_dengue.ui.auth_screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.focos_dengue.R
import com.focos_dengue.ui.theme.AppTheme
import com.focos_dengue.ui.util.PrimaryDivider
import com.focos_dengue.ui.util.XS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopbar() {
    val colors = AppTheme.colors
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Entrar",
                    modifier = Modifier.padding(horizontal = XS),
                    color = colors.onBackground,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colors.background,
                titleContentColor = colors.onBackground
            ),
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "Entrar",
                    modifier = Modifier.padding(XS)
                )
            }
        )

        PrimaryDivider()
    }
}
