package com.foco_acessibilidade_dengue.ui.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AuthInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    iconId: Int,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = label
            )
        }
    )
}
