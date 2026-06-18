package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryText
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.XL

@Composable
fun ReportCard(report: ReportModel) {
    PrimaryCard(horizontalPadding = SM, verticalPadding = SM) {
        Box(modifier = Modifier.fillMaxSize().height(8 * XL)) {
            AsyncImage(
                model = report.imageUri,
                contentDescription = "Foto da denúncia",
                modifier = Modifier.fillMaxSize()
            )
        }
        PrimaryText("Descrição: ${report.description}")
        PrimaryText("Local: ${report.location.address}")
    }
}
