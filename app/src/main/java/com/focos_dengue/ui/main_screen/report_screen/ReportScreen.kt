package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.focos_dengue.ui.main_screen.VerticalScrollableContent

@Composable
fun ReportScreen(toAccountScreen: () -> Unit) {
    VerticalScrollableContent(
        bottomBar = {
            ReportScreenBottomBar(toAccountScreen = toAccountScreen)
        }
    ) { innerPadding, scrollState ->
        ReportScreenContent(
            modifier = Modifier.padding(innerPadding),
            scrollState = scrollState
        )
    }
}
