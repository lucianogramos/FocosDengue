package com.focos_dengue.ui.report_screen

import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.focos_dengue.R
import com.focos_dengue.ui.components.ParagraphText
import com.focos_dengue.ui.components.PrimaryCard
import com.focos_dengue.ui.components.PrimaryIcon
import com.focos_dengue.ui.components.SecondaryCard
import com.focos_dengue.ui.utils.DP_0
import com.focos_dengue.ui.utils.MD
import com.focos_dengue.ui.utils.ROUNDED_MD
import com.focos_dengue.ui.utils.SM
import com.focos_dengue.ui.utils.TEXT_SM
import com.focos_dengue.ui.utils.XL
import com.focos_dengue.ui.utils.XS
import com.focos_dengue.ui.utils.dashedBorder

@Composable
fun LocationCard() {
    val colorScheme = MaterialTheme.colorScheme

    PrimaryCard {
        Spacer(Modifier.height(SM))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                PrimaryIcon(R.drawable.location_icon, "Localização")
                Spacer(Modifier.width(XS))
                Text("Localização", color = colorScheme.onBackground)
            }

            Text("Alterar", color = colorScheme.onBackground, textDecoration = TextDecoration.Underline)
        }

        Spacer(Modifier.height(SM))

        SecondaryCard(
            modifier = Modifier.fillMaxWidth().height(8 * XL),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Mapa do Google", color = colorScheme.onBackground)
        }

        ParagraphText(
            text = "Bairro Alcides Junqueira\nItuiutaba - MG",
            fontSize = TEXT_SM,
            marginTop = SM
        )
    }
}

@Composable
fun PhotoCard(
    height: Dp,
    dashLength: Dp = SM,
    gapLength: Dp = XS,
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
            }
            else {
                Toast.makeText(context, "Extensão de arquivo .$extension inválida", Toast.LENGTH_LONG).show()
                photoUri = null
            }
        }
    }

    val modifier = Modifier.fillMaxWidth().height(height)
        .dashedBorder(
            color = colorScheme.outline,
            shape = RoundedCornerShape(ROUNDED_MD),
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
                shape = RoundedCornerShape(ROUNDED_MD)
            )
        ) {
            AsyncImage(
                model = photoUri,
                contentDescription = "Foto Selecionada",
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(end = MD, top = MD),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    modifier = Modifier.size(XL),
                    shape = CircleShape,
                    contentPadding = PaddingValues(DP_0),
                    onClick = {
                        photoUri = null
                        Toast.makeText(context, "Foto removida", Toast.LENGTH_LONG).show()
                        onClick?.invoke(null)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.background
                    )
                ) {
                    PrimaryIcon(R.drawable.remove_icon, "Remover Foto")
                }
            }
        }
    }
    else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PrimaryIcon(R.drawable.photo_camera_icon, "Adicionar Foto")

            Spacer(Modifier.height(MD))

            Text("Adicionar Foto", color = colorScheme.onBackground)
            ParagraphText("Adicione uma foto jpg/jpeg", fontSize = TEXT_SM)
        }
    }
}
