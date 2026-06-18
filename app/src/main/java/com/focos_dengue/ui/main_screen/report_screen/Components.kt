package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.runtime.Composable
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryText
import com.focos_dengue.ui.util.SM

@Composable
fun ReportCard(report: ReportModel) {
    PrimaryCard(horizontalPadding = SM, verticalPadding = SM) {
        PrimaryText("Descrição: ${report.description}")
        PrimaryText("Local: ${report.location.address}")
    }
}
