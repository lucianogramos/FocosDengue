package com.focos_dengue.ui.auth_screen.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.auth_screen.ClickHereLink
import com.focos_dengue.ui.auth_screen.PasswordTextField
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TEXT_MD
import com.focos_dengue.ui.util.XL
import com.focos_dengue.ui.util.XS

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    toLoginScreen: () -> Unit,
    toReportScreen: () -> Unit,
    state: SignUpUIState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUp: (() -> Unit) -> Result<Unit>
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(2 * MD)) {
        TitleText(
            text = "Bem-vindo",
            marginTop = XS
        )

        SecondaryText(
            text = "Faça seu cadastro para poder denunciar locais com foco de dengue e falta de acessibilidade",
            fontSize = TEXT_MD,
            marginTop = SM,
            marginBottom = XL
        )

        PrimaryTextField(
            value = state.name,
            onValueChange = onNameChange,
            label = "Nome"
        )

        Spacer(Modifier.height(LG))

        PrimaryTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "E-mail",
            placeholder = "email@exemplo.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.height(LG))

        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            hasMinChars = state.passwordRequirements.hasMinChars,
            hasUpperCase = state.passwordRequirements.hasUpperCase,
            hasLowerCase = state.passwordRequirements.hasLowerCase,
            hasNumber = state.passwordRequirements.hasNumber,
            hasSpecialChar = state.passwordRequirements.hasSpecialChar
        )

        Spacer(Modifier.height(XL))

        PrimaryButton(text = "Cadastrar", onClick = {
            onSignUp {
                toReportScreen()
            }.onFailure {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        })

        Spacer(Modifier.height(XS))

        ClickHereLink("Já tem uma conta? ", "navigation") { toLoginScreen() }
    }
}
