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
import com.focos_dengue.ui.util.PrimaryTextField
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TEXT_XS
import com.focos_dengue.ui.util.XS

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
        visualTransformation = PasswordVisualTransformation(), // Esconde o texto com bolinhas
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    SecondaryText(text = "Contém no mínimo 8 caracteres", fontSize = TEXT_XS, marginTop = XS)
    SecondaryText(text = "Contém pelo menos uma letra maiúscula", fontSize = TEXT_XS)
    SecondaryText(text = "Contém pelo menos uma letra minúscula", fontSize = TEXT_XS)
    SecondaryText(text = "Contém pelo menos um número", fontSize = TEXT_XS)
    SecondaryText(text = "Contém pelo menos um caractere especial", fontSize = TEXT_XS)
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
