package com.focos_dengue.ui.main_screen.report_screen

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.focos_dengue.ui.main_screen.VerticalScrollableContent

@Composable
fun ReportScreen(toAccountScreen: () -> Unit, viewModel: ReportViewModel = viewModel()) {
    val context = LocalContext.current

    VerticalScrollableContent(
        bottomBar = {
            ReportScreenBottomBar(toAccountScreen)
        }
    ) { innerPadding, scrollState ->
        ReportScreenContent(
            modifier = Modifier.padding(innerPadding),
            scrollState = scrollState,
            state = viewModel.uiState,
            onDescriptionChange = viewModel::updateDescription,
            onPhotoUriChange = viewModel::updatePhotoUri,
            onSendReport = {
                viewModel.onSendReport { result ->
                    when (result) {
                        is SendResult.Success ->
                            Toast.makeText(context, "Denúncia enviada", Toast.LENGTH_LONG).show()
                        is SendResult.Error ->
                            Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }
}
