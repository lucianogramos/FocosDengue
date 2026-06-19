package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
import com.focos_dengue.ui.util.zoomableAndPannable

@Composable
fun ReportCard(report: ReportUIModel) {
    Spacer(Modifier.height(MD))

    var isExpanded by remember { mutableStateOf(false) }

    PrimaryCard(horizontalPadding = SM, verticalPadding = SM) {
        Box(modifier = Modifier.fillMaxWidth()
            .height(8 * XL)
            .border(
                border = BorderStroke(BORDER_WIDTH, AppTheme.colors.outline),
                shape = RoundedCornerShape(ROUNDED_MD)
            )
        ) {
            AsyncImage(
                model = report.imageUri,
                contentDescription = "Foto da denúncia",
                modifier = Modifier.fillMaxSize().clickable { isExpanded = true }
            )
        }

        PrimaryText("Descrição: ${report.description}", marginTop = XS)
        SecondaryText(report.address, marginTop = XS, marginBottom = XS)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            SecondaryText(report.createdAt)
        }
    }

    if (isExpanded) {
        Dialog(
            onDismissRequest = { isExpanded = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().clickable { isExpanded = false },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = report.imageUri,
                    contentDescription = "Foto da denúncia ampliada",
                    modifier = Modifier.fillMaxWidth().zoomableAndPannable()
                        .clickable(enabled = false, onClick =  {}),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
