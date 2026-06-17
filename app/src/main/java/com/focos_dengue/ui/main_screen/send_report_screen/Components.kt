package com.focos_dengue.ui.main_screen.send_report_screen

import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.focos_dengue.R
import com.focos_dengue.ui.theme.AppTheme
import com.focos_dengue.ui.util.BORDER_WIDTH
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryIcon
import com.focos_dengue.ui.util.DP_0
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PrimaryText
import com.focos_dengue.ui.util.ROUNDED_MD
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TEXT_MD
import com.focos_dengue.ui.util.XL
import com.focos_dengue.ui.util.XS
import com.focos_dengue.ui.util.dashedBorder
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun LocationCard(
    neighborhood: String,
    street: String,
    cameraPositionState: CameraPositionState,
    initialLocation: LatLng,
    onLocationSelected: (LatLng) -> Unit
) {
    PrimaryCard {
        Spacer(Modifier.height(SM))

        Row {
            PrimaryIcon(R.drawable.location_icon, "Localização")
            Spacer(Modifier.width(XS))
            PrimaryText("Localização")
        }

        Spacer(Modifier.height(SM))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8 * XL)
        ) {
            MapPicker(cameraPositionState, initialLocation, onLocationSelected)
        }

        SecondaryText(text = "$street - $neighborhood\nItuiutaba - MG", marginTop = SM)
    }
}

@Composable
fun MapPicker(
    cameraPositionState: CameraPositionState,
    initialLocation: LatLng,
    onLocationSelected: (LatLng) -> Unit
) {
    LaunchedEffect(initialLocation) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(initialLocation, 15f)
    }

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }

    GoogleMap(
        modifier = Modifier.fillMaxSize().border(
            width = BORDER_WIDTH,
            color = AppTheme.colors.outlineVariant
        ),
        mapColorScheme = ComposeMapColorScheme.FOLLOW_SYSTEM,
        uiSettings = MapUiSettings(tiltGesturesEnabled = false),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            markerPosition = latLng
            onLocationSelected(latLng)
        }
    ) {
        markerPosition?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Local da Denúncia",
                snippet = "Clique para alterar"
            )
        }
    }
}

@Composable
fun PhotoCard(
    height: Dp,
    dashLength: Dp = SM,
    gapLength: Dp = XS,
    onClick: ((Uri?) -> Unit)? = null
) {
    val colors = AppTheme.colors
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
        onClick?.invoke(photoUri)
    }

    val modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .dashedBorder(
            color = colors.outline,
            shape = RoundedCornerShape(ROUNDED_MD),
            dashLength = dashLength,
            gapLength = gapLength
        )
        .clickable(onClick = {
            launcher.launch("image/jpeg")
        })

    if (photoUri != null) {
        Box(
            modifier = modifier.background(
                color = colors.secondary,
                shape = RoundedCornerShape(ROUNDED_MD)
            )
        ) {
            AsyncImage(
                model = photoUri,
                contentDescription = "Foto Selecionada",
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MD, top = MD),
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
                        containerColor = colors.background
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

            PrimaryText("Adicionar Foto", fontSize = TEXT_MD)
            SecondaryText("Adicione uma foto jpg/jpeg")
        }
    }
}
