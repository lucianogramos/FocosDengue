package com.focos_dengue.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.focos_dengue.R
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

    PasswordTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChar) onValueChange(it)
        },
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        supportingText = supportingText
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Senha",
    placeholder: String = "Digite sua senha...",
    supportingText: @Composable (() -> Unit)? = null
) {
    val visibilityIconId = R.drawable.visibility_icon
    val visibilityOffIconId = R.drawable.visibility_off_icon

    var iconId by remember { mutableIntStateOf(visibilityIconId) }

    PrimaryTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        trailingIcon = {
            PrimaryIconButton(
                iconId = iconId,
                contentDescription = "Mostrar/Esconder senha"
            ) {
                iconId =
                    if (iconId == visibilityIconId)
                        visibilityOffIconId
                    else
                        visibilityIconId
            }
        },
        visualTransformation =
            if (iconId == visibilityIconId)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        supportingText = supportingText
    )
}
