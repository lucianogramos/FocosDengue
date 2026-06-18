package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReportScreen(toAccountScreen: () -> Unit, viewModel: ReportScreenViewModel = viewModel()) {
    Scaffold(
        bottomBar = {
            ReportScreenBottomBar(toAccountScreen = toAccountScreen)
        }
    ) { innerPadding ->
        ReportScreenContent(
            modifier = Modifier.padding(innerPadding),
            state = viewModel.uiState,
            loadNextReports = viewModel::loadNextReports
        )
    }
}
