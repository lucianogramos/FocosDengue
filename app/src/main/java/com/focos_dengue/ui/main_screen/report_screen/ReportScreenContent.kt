package com.focos_dengue.ui.main_screen.report_screen

import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.XS
import com.focos_dengue.ui.util.XL
import com.focos_dengue.ui.util.PrimaryButton
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryTextFieldWithCounter
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.SubtitleText
import com.focos_dengue.ui.util.TEXT_MD
import com.focos_dengue.ui.util.TitleText
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    state: ReportUIState,
    onDescriptionChange: (String) -> Unit,
    onPhotoUriChange: (Uri?) -> Unit,
    onSendReport: () -> Unit
) {
    val maxCharsOfDescription = 200
    val cameraPositionState = rememberCameraPositionState()

    Column(modifier = modifier.fillMaxSize()
        .verticalScroll(scrollState, !cameraPositionState.isMoving)
        .padding(2 * MD)
    ) {
        TitleText("Novo reporte", marginTop = XS)

        SecondaryText(
            text = "Envie fotos do problema para os orgãos públicos",
            fontSize = TEXT_MD,
            marginTop = SM,
            marginBottom = MD
        )

        LocationCard(cameraPositionState, LatLng(-18.96889, -49.46500))

        SubtitleText("Descrição", marginTop = MD, marginBottom = MD)

        PrimaryTextFieldWithCounter(
            value = state.description,
            onValueChange = onDescriptionChange,
            maxChar = maxCharsOfDescription,
            height = 4 * XL,
            maxLines = 5,
            placeholder = "Descreva o problema encontrado..."
        )

        SubtitleText("Fotos do Problema", marginTop = MD, marginBottom = MD)

        PhotoCard(height = 8 * XL, onClick = onPhotoUriChange)

        Spacer(Modifier.height(MD))

        PrimaryCard {
            SecondaryText(
                text = "Essa denúncia será enviada para o seguinte destinatário:",
                marginBottom = XS
            )
            SecondaryText("Centro de Controle de Zoonoses")
        }

        Spacer(Modifier.height(LG))

        PrimaryButton(text = "Enviar Denúncia", onClick = onSendReport)
    }
}

@Preview
@Composable
fun ReportScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ReportScreenContent(
                scrollState = rememberScrollState(),
                state = ReportUIState(),
                onDescriptionChange = {},
                onPhotoUriChange = {},
                onSendReport = {}
            )
        }
    }
}