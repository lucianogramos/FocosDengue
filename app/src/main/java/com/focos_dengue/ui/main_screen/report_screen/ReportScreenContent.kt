package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TEXT_MD
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.XS

@Composable
fun ReportScreenContent(
    modifier: Modifier = Modifier,
    state: ReportUIState,
    loadNextReports: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize().padding(2 * MD)) {
        TitleText("Denúncias", marginTop = XS)
        SecondaryText(
            text = "Veja as denúncias que já foram enviadas",
            fontSize = TEXT_MD,
            marginTop = SM,
            marginBottom = MD
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.reports) { report ->
                ReportCard(report)
            }

            item {
                if (!state.isEndReached) {
                    LaunchedEffect(Unit) {
                        loadNextReports()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
