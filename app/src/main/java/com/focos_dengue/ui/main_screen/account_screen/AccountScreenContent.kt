package com.focos_dengue.ui.main_screen.account_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PasswordTextField
import com.focos_dengue.ui.util.PrimaryAlertButton
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryDivider
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.SubtitleText
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.XS

@Composable
fun AccountScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    state: AccountUIState,
    onUpdateNewEmail: (String) -> Unit,
    onUpdateNewPassword: (String) -> Unit,
    onUpdateConfirmationPassword: (String) -> Unit,
    onChangeEmail: () -> Unit,
    onChangePassword: () -> Unit,
    onLogout: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState).padding(2 * MD)) {
        TitleText("Configurações da Conta", marginBottom = LG)

        PrimaryCard {
            PrimaryTextField(
                value = state.newEmailValue,
                onValueChange = onUpdateNewEmail,
                label = "Novo E-mail",
                placeholder = "Digite seu novo e-mail...",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(Modifier.height(SM))

            PrimaryButton("Alterar E-mail", onClick = onChangeEmail)
        }

        PrimaryDivider(marginTop = SM, marginBottom = SM)

        PrimaryCard {
            PasswordTextField(
                value = state.newPasswordValue,
                onValueChange = onUpdateNewPassword,
                label = "Nova Senha",
                placeholder = "Digite sua nova senha...",
                hasMinChars = state.passwordRequirements.hasMinChars,
                hasUpperCase = state.passwordRequirements.hasUpperCase,
                hasLowerCase = state.passwordRequirements.hasLowerCase,
                hasNumber = state.passwordRequirements.hasNumber,
                hasSpecialChar = state.passwordRequirements.hasSpecialChar
            )

            Spacer(Modifier.height(SM))

            PrimaryTextField(
                value = state.confirmationPasswordValue,
                onValueChange = onUpdateConfirmationPassword,
                label = "Confirmar Senha",
                placeholder = "Confirme sua nova senha...",
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(Modifier.height(SM))

            PrimaryButton(text = "Alterar Senha", onClick = onChangePassword)
        }

        PrimaryDivider(marginTop = SM, marginBottom = SM)

        PrimaryCard {
            SubtitleText("Sair da Conta", marginBottom = XS)
            SecondaryText("Deseja sair da conta?", marginBottom = SM)
            PrimaryButton(text = "Sair da Conta", onClick = onLogout)
        }

        PrimaryDivider(marginTop = SM, marginBottom = SM)

        PrimaryCard {
            SubtitleText("Excluir conta", marginBottom = XS)
            SecondaryText(
                text = "Isso excluirá sua conta permanentemente. Essa ação não pode ser desfeita.",
                marginBottom = SM
            )
            PrimaryAlertButton(text = "Excluir Conta", onClick = {})
        }
    }
}
