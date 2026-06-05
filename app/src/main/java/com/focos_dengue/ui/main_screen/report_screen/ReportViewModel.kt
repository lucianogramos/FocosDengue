package com.focos_dengue.ui.main_screen.report_screen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ReportUIState (
    val description: String = "",
    val photoUri: Uri? = null
)

class ReportViewModel : ViewModel() {
    var uiState by mutableStateOf(ReportUIState())
        private set

    fun updateDescription(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun updatePhotoUri(uri: Uri?) {
        uiState = uiState.copy(photoUri = uri)
    }
}