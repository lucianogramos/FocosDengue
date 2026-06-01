package com.focos_dengue.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.utils.BORDER_WIDTH
import com.focos_dengue.ui.utils.DP_0
import com.focos_dengue.ui.utils.MD
import com.focos_dengue.ui.utils.ROUNDED_MD
import com.focos_dengue.ui.utils.SM
import com.focos_dengue.ui.utils.TEXT_MD
import com.focos_dengue.ui.utils.TEXT_SM
import com.focos_dengue.ui.utils.TEXT_XL

@Composable
fun VerticalMargin(margintTop: Dp, marginBottom: Dp, content: @Composable () -> Unit) {
    if (margintTop > DP_0) Spacer(Modifier.height(margintTop))
    content()
    if (marginBottom > DP_0) Spacer(Modifier.height(marginBottom))
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(ROUNDED_MD),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = TEXT_XL, marginTop: Dp = DP_0, marginBottom: Dp = DP_0) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SubtitleText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = TEXT_MD, marginTop: Dp = DP_0, marginBottom: Dp = DP_0) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize)
    }
}

@Composable
fun ParagraphText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = TEXT_MD, marginTop: Dp = DP_0, marginBottom: Dp = DP_0) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onSecondary, fontSize = fontSize)
    }
}

@Composable
fun PrimaryText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = TEXT_SM, marginTop: Dp = DP_0, marginBottom: Dp = DP_0) {
    VerticalMargin(marginTop, marginBottom) {
        Text(text, modifier, MaterialTheme.colorScheme.onBackground, fontSize = fontSize)
    }
}

@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = MD,
    verticalPadding: Dp = SM,
    content: @Composable () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(ROUNDED_MD),
        border = BorderStroke(BORDER_WIDTH, colorScheme.outline),
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
        shape = RoundedCornerShape(ROUNDED_MD),
        border = BorderStroke(BORDER_WIDTH, colorScheme.outline),
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
fun TextFieldWithCounter(
    value: String,
    onValueChange: (String) -> Unit,
    maxChar: Int,
    modifier: Modifier = Modifier,
    height: Dp,
    maxLines: Int = 1,
    placeholder: String = ""
) {
    val colorScheme = MaterialTheme.colorScheme
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) colorScheme.primary else colorScheme.outline
    val borderWidth = if (isFocused) 2 * BORDER_WIDTH else BORDER_WIDTH

    Column(
        modifier = modifier
            .fillMaxWidth().height(height)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(ROUNDED_MD))
            .background(colorScheme.background, RoundedCornerShape(ROUNDED_MD))
            .padding(bottom = SM)
    ) {
        TextField(
            value = value,
            onValueChange = {
                if (it.length <= maxChar) onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth().weight(1f).onFocusChanged { isFocused = it.isFocused },
            maxLines = maxLines,
            placeholder = if (placeholder.isNotEmpty()) { { Text(placeholder) } } else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = colorScheme.onSecondary,
                unfocusedPlaceholderColor = colorScheme.onSecondary,
                focusedTextColor = colorScheme.onBackground,
                unfocusedTextColor = colorScheme.onBackground
            )
        )

        Text(
            text = "${value.length} / $maxChar",
            color = colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.End).padding(end = MD)
        )
    }
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
            shape = RoundedCornerShape(ROUNDED_MD),
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
            shape = RoundedCornerShape(ROUNDED_MD),
            border = BorderStroke(BORDER_WIDTH, colorScheme.outline)
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

@Composable
fun PrimaryIcon(iconId: Int, contentDescription: String?) {
    Icon(
        painter = painterResource(iconId),
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.onBackground
    )
}
