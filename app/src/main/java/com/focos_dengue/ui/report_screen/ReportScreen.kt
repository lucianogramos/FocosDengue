package com.focos_dengue.ui.report_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReportScreen() {
    Scaffold() { innerPadding ->
        ReportScreenContent(Modifier.padding(innerPadding))
    }
}