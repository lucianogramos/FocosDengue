package com.focos_dengue.ui.auth_screen.forgot_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.focos_dengue.R
import com.focos_dengue.ui.auth_screen.PasswordTextField
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.NumericField
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.PrimaryIconButton
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.SubtitleText
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.XL

@Composable
fun ForgotPasswordContent(
    modifier: Modifier = Modifier,
    toLoginScreen: () -> Unit,
    state: ForgotPasswordUIState,
    onPasswordChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onSendCode: () -> Unit,
    onVerifyCode: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().padding(2 * MD)) {
        PrimaryIconButton(
            iconId = R.drawable.arrow_back,
            contentDescription = "Voltar para a tela de Login",
            onClick = toLoginScreen
        )

        TitleText("Recuperação de Senha", marginTop = MD, marginBottom = LG)

        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "Nova Senha",
            placeholder = "Digite sua nova senha...",
            hasMinChars = state.passwordRequirements.hasMinChars,
            hasUpperCase = state.passwordRequirements.hasUpperCase,
            hasLowerCase = state.passwordRequirements.hasLowerCase,
            hasNumber = state.passwordRequirements.hasNumber,
            hasSpecialChar = state.passwordRequirements.hasSpecialChar
        )

        Spacer(Modifier.height(MD))

        PrimaryButton(text = "Enviar Código", onClick = onSendCode)

        if (state.codeSent) {
            SubtitleText("Digite o código de 6 dígitos", marginTop = LG)
            SecondaryText(
                text = "Um código de 6 digitos foi enviado para o seu e-mail. Verifique sua caixa de e-mails",
                marginBottom = MD
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                NumericField(state.code, onCodeChange, modifier = Modifier.width(4 * XL))
            }

            Spacer(Modifier.height(MD))

            PrimaryButton(text = "Verificar Código", onClick = onVerifyCode)
        }
    }
}

@Preview
@Composable
fun ForgotPasswordContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ForgotPasswordContent(
                toLoginScreen = {},
                state = ForgotPasswordUIState(),
                onPasswordChange = {},
                onCodeChange = {},
                onSendCode = {},
                onVerifyCode = {}
            )
        }
    }
}
