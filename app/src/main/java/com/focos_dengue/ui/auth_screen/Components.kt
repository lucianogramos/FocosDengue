package com.focos_dengue.ui.auth_screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink

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
