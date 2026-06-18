package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.focos_dengue.R
import com.focos_dengue.ui.theme.AppTheme
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.PrimaryIconButton
import com.focos_dengue.ui.util.XS

@Composable
fun ReportScreen(
    toAccountScreen: () -> Unit,
    toSendReportScreen: () -> Unit,
    viewModel: ReportScreenViewModel = viewModel()
) {
    Scaffold(
        bottomBar = {
            ReportScreenBottomBar(toAccountScreen = toAccountScreen)
        },
        floatingActionButton = {
            PrimaryIconButton(
                iconId = R.drawable.add_icon,
                contentDescription = "Adicionar denúncia",
                modifier = Modifier.padding(end = XS)
                    .background(
                        color = AppTheme.colors.primary,
                        shape = CircleShape
                    ).padding(LG),
                onClick = toSendReportScreen
            )
        }
    ) { innerPadding ->
        ReportScreenContent(
            modifier = Modifier.padding(innerPadding),
            state = viewModel.uiState,
            loadNextReports = viewModel::loadNextReports
        )
    }
}
