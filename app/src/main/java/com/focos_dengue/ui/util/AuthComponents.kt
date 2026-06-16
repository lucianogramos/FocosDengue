package com.focos_dengue.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.focos_dengue.ui.theme.AppTheme

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hasMinChars: Boolean,
    hasUpperCase: Boolean,
    hasLowerCase: Boolean,
    hasNumber: Boolean,
    hasSpecialChar: Boolean,
    modifier: Modifier = Modifier,
    label: String = "Senha",
    placeholder: String = "Digite sua senha..."
) {
    val maxChar = 20

    val requirements = listOf(hasMinChars, hasUpperCase, hasLowerCase, hasNumber, hasSpecialChar)
    var requirementsCompleted = 0
    for (i in requirements) {
        if (i) requirementsCompleted++
    }

    val supportingText = @Composable {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (requirementsCompleted == requirements.size)
                SuccessText("Sua senha é forte", fontSize = TEXT_XS)
            else if (requirementsCompleted > 2)
                WarningText("Sua senha é média", fontSize = TEXT_XS)
            else
                ErrorText("Sua senha é fraca", fontSize = TEXT_XS)

            Text(
                text = "${value.length} / $maxChar caracteres",
                color = AppTheme.colors.onSecondary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    PrimaryTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        supportingText = supportingText
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            PasswordTextField(
                "",
                {},
                true,
                true,
                true,
                true,
                true
            )
        }
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Senha",
    placeholder: String = "Digite sua senha..."
) {
    PrimaryTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
