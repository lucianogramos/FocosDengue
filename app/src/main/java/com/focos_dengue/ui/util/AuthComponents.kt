package com.focos_dengue.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.focos_dengue.R

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
        supportingText = {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = "${value.length} / $maxChar caracteres",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )

    val color = @Composable { isValid: Boolean ->
        if (isValid) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.error
    }

    IconText(
        text = "Contém no mínimo 8 caracteres", iconId = R.drawable.check_circle,
        iconTint = color(hasMinChars), textColor = color(hasMinChars),
        fontSize = TEXT_XS, iconSize = LG, marginTop = XS
    )
    IconText(
        text = "Contém no mínimo uma letra maiúscula", iconId = R.drawable.check_circle,
        iconTint = color(hasUpperCase), textColor = color(hasUpperCase),
        fontSize = TEXT_XS, iconSize = LG
    )
    IconText(
        text = "Contém no mínimo uma letra minúscula", iconId = R.drawable.check_circle,
        iconTint = color(hasLowerCase), textColor = color(hasLowerCase),
        fontSize = TEXT_XS, iconSize = LG
    )
    IconText(
        text = "Contém no mínimo um número", iconId = R.drawable.check_circle,
        iconTint = color(hasNumber), textColor = color(hasNumber),
        fontSize = TEXT_XS, iconSize = LG
    )
    IconText(
        text = "Contém no mínimo um caractere especial", iconId = R.drawable.check_circle,
        iconTint = color(hasSpecialChar), textColor = color(hasSpecialChar),
        fontSize = TEXT_XS, iconSize = LG
    )
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
