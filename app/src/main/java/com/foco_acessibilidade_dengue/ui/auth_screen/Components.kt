package com.foco_acessibilidade_dengue.ui.auth_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = colorScheme.onBackground,
            unfocusedLabelColor = colorScheme.onSecondary,
            focusedContainerColor = colorScheme.background,
            unfocusedContainerColor = colorScheme.background,
            focusedPlaceholderColor = colorScheme.onSecondary,
            unfocusedPlaceholderColor = colorScheme.onSecondary,
            focusedTextColor = colorScheme.onBackground,
            unfocusedTextColor = colorScheme.onBackground,
            focusedBorderColor = colorScheme.primary,
            unfocusedBorderColor = colorScheme.outline
        )
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
