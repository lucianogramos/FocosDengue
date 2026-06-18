package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.focos_dengue.ui.theme.AppTheme
import com.focos_dengue.ui.util.BORDER_WIDTH
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PrimaryCard
import com.focos_dengue.ui.util.PrimaryText
import com.focos_dengue.ui.util.ROUNDED_MD
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.XL
import com.focos_dengue.ui.util.XS

@Composable
fun ReportCard(report: ReportUIModel) {
    Spacer(Modifier.height(MD))

    PrimaryCard(horizontalPadding = SM, verticalPadding = SM) {
        Box(modifier = Modifier.fillMaxSize()
            .height(8 * XL)
            .border(
                border = BorderStroke(BORDER_WIDTH, AppTheme.colors.outline),
                shape = RoundedCornerShape(ROUNDED_MD)
            )
        ) {
            AsyncImage(
                model = report.imageUri,
                contentDescription = "Foto da denúncia",
                modifier = Modifier.fillMaxSize()
            )
        }

        PrimaryText("Descrição: ${report.description}", marginTop = XS)
        SecondaryText(report.address, marginTop = XS, marginBottom = XS)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            SecondaryText(report.createdAt)
        }
    }
}
