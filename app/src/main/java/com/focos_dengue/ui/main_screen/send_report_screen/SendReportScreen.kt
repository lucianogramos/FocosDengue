package com.focos_dengue.ui.main_screen.send_report_screen

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SendReportScreen(
    toReportScreen: () -> Unit,
    viewModel: SendReportViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold { innerPadding ->
        SendReportScreenContent(
            modifier = Modifier.padding(innerPadding),
            toReportScreen = toReportScreen,
            state = viewModel.uiState,
            onDescriptionChange = viewModel::updateDescription,
            onTypeChange = viewModel::updateSelectedType,
            onPhotoUriChange = viewModel::updatePhotoUri,
            onLocationChange = viewModel::updateLocation,
            onSendReport = {
                viewModel.onSendReport { result ->
                    when (result) {
                        is SendReportResult.Success ->
                            Toast.makeText(context, "Denúncia enviada", Toast.LENGTH_LONG).show()
                        is SendReportResult.Error ->
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }
}
