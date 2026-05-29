package com.foco_acessibilidade_dengue.ui.report_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foco_acessibilidade_dengue.R
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.PrimaryCard
import com.foco_acessibilidade_dengue.ui.utils.dashedBorder

@Composable
fun LocationCard() {
    val colorScheme = MaterialTheme.colorScheme

    PrimaryCard {
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.add_location_icon),
                    contentDescription = "Localização",
                    tint = colorScheme.onBackground
                )
                Spacer(Modifier.width(4.dp))
                Text("Localização", color = colorScheme.onBackground)
            }

            Text("Alterar", color = colorScheme.onBackground, textDecoration = TextDecoration.Underline)
        }

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(256.dp)
                .background(colorScheme.tertiary, RoundedCornerShape(8.dp))
                .border(BorderStroke(1.dp, colorScheme.outline), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Mapa do Google", color = colorScheme.onBackground)
        }

        ParagraphText(
            text = "Bairro Alcides Junqueira\nItuiutaba - MG",
            fontSize = 14.sp,
            marginTop = 8.dp
        )
    }
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
    val borderWidth = if (isFocused) 2.dp else 1.dp

    Column(
        modifier = modifier
            .fillMaxWidth().height(height)
            .border(BorderStroke(borderWidth, borderColor), RoundedCornerShape(8.dp))
            .background(colorScheme.background, RoundedCornerShape(8.dp))
            .padding(bottom = 8.dp)
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
            modifier = Modifier.align(Alignment.End).padding(end = 12.dp)
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

@Composable
fun PhotoCard(
    height: Dp
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .dashedBorder(
                color = colorScheme.outline,
                shape = RoundedCornerShape(8.dp),
                dashLength = 8.dp,
                gapLength = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.photo_camera_icon),
            contentDescription = "Adicionar Foto",
            tint = colorScheme.onBackground
        )

        Spacer(Modifier.height(12.dp))

        Text("Adicionar Foto", color = colorScheme.onBackground)
    }
}

@Preview
@Composable
fun PhotoCardPreview() {
    MaterialTheme {
        Surface (color = MaterialTheme.colorScheme.background) {
            PhotoCard(height = 128.dp)
        }
    }
}
