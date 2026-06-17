package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.SecondaryText
import com.focos_dengue.ui.util.TitleText
import com.focos_dengue.ui.util.XS

@Composable
fun ReportScreenContent(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState).padding(2 * MD)) {
        TitleText("Denúncias", marginTop = XS)
        SecondaryText("Veja as denúncias que já foram enviadas")

        
    }
}
