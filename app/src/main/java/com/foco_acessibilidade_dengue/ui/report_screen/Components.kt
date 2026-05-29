package com.foco_acessibilidade_dengue.ui.report_screen

import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foco_acessibilidade_dengue.R
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.PrimaryCard
import com.foco_acessibilidade_dengue.ui.components.SecondaryCard
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

        SecondaryCard(
            modifier = Modifier.fillMaxWidth().height(256.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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

@Composable
fun PhotoCard(
    height: Dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 4.dp,
    onClick: ((uri: Uri?) -> Unit)? = null
) {
    val colorScheme = MaterialTheme.colorScheme
    val context = LocalContext.current
    var photoUri: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        photoUri = uri
        if (uri != null) {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri)
            val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)

            if (extension == "jpg" || extension == "jpeg") {
                Toast.makeText(context, "Foto selecionada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Extensão de arquivo .$extension inválida", Toast.LENGTH_LONG).show()
                photoUri = null
            }
        }
    }

    val modifier = Modifier.fillMaxWidth().height(height)
        .dashedBorder(
            color = colorScheme.outline,
            shape = RoundedCornerShape(8.dp),
            dashLength = dashLength,
            gapLength = gapLength
        )
        .clickable(onClick = {
            launcher.launch("image/jpeg")
            onClick?.invoke(photoUri)
        })

    if (photoUri != null) {
        Box(
            modifier = modifier.background(
                color = colorScheme.secondary,
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 12.dp, top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    modifier = Modifier.size(32.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        photoUri = null
                        onClick?.invoke(null)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.background
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.remove_icon),
                        contentDescription = "Remover Foto",
                        tint = colorScheme.onBackground
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.image_icon),
                    contentDescription = "Foto Selecionada",
                    tint = colorScheme.onBackground
                )

                Spacer(Modifier.height(8.dp))

                Text("Foto Selecionada", color = colorScheme.onBackground)
            }
        }
    }
    else {
        Column(
            modifier = modifier,
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
            ParagraphText("Adicione uma foto jpg/jpeg", fontSize = 14.sp)
        }
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
