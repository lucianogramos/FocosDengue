package com.foco_acessibilidade_dengue.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerticalMargin(margintTop: Dp, marginBottom: Dp, content: @Composable () -> Unit) {
    if (margintTop > 0.dp) Spacer(Modifier.height(margintTop))
    content()
    if (marginBottom > 0.dp) Spacer(Modifier.height(marginBottom))
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 32.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SubtitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize)
    }
}

@Composable
fun ParagraphText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onSecondary, fontSize = fontSize)
    }
}

@Composable
fun PrimaryText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 14.sp, marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize)
    }
}

@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 12.dp,
    verticalPadding: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, colorScheme.outline),
        colors = CardDefaults.outlinedCardColors(
            containerColor = colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
        ) {
            content()
        }
    }
}

@Composable
fun SecondaryCard(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, colorScheme.outline),
        colors = CardDefaults.outlinedCardColors(
            containerColor = colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            content()
        }
    }
}

@Composable
fun PrimaryDivider() {
    val colorScheme = MaterialTheme.colorScheme

    val gradientColors = listOf(
        Color.Transparent,
        colorScheme.outline,
        colorScheme.outline,
        colorScheme.outline,
        Color.Transparent
    )

    HorizontalDivider(
        thickness = 1.5.dp,
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(colors = gradientColors)
        ),
        color = Color.Transparent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    selectedValue: String,
    setSelectedValue: (String) -> Unit,
    options: List<String> = emptyList(),
    label: String = "",
) {
    val colorScheme = MaterialTheme.colorScheme

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true, // Torna o campo imutável via teclado
            label = if (!label.isEmpty()) { { Text(label, color = colorScheme.onBackground) } } else null,
            // O menuAnchor() liga fisicamente o menu a este campo de texto
            modifier = Modifier.fillMaxWidth().menuAnchor(
                type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                enabled = true
            ),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorScheme.primary,
                unfocusedBorderColor = colorScheme.outline,
                focusedTrailingIconColor = colorScheme.onBackground,
                unfocusedTrailingIconColor = colorScheme.onBackground,
                focusedLabelColor = colorScheme.onBackground,
                unfocusedLabelColor = colorScheme.onBackground,
                focusedContainerColor = colorScheme.background,
                unfocusedContainerColor = colorScheme.background
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
            containerColor = colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, colorScheme.outline)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        setSelectedValue(option)
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
