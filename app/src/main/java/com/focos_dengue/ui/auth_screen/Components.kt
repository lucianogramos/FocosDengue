package com.focos_dengue.ui.auth_screen

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import com.focos_dengue.R
import com.focos_dengue.ui.util.IconText
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.TEXT_XS
import com.focos_dengue.ui.util.XS

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

    PrimaryTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        placeholder = placeholder,
        visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
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
fun ClickHereLink(text: String, tag: String, onClick: LinkInteractionListener?) {
    val link = buildAnnotatedString {
        append(text)
        withLink(
            link = LinkAnnotation.Clickable(
                tag = tag,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = onClick
            )
        ) {
            append("Clique aqui")
        }
    }

    Text(link, color = MaterialTheme.colorScheme.onSecondary)
}
