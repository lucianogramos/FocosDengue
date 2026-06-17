package com.focos_dengue.ui.main_screen.send_report_screen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.model.ReportType
import com.focos_dengue.domain.model.toReportType
import com.focos_dengue.domain.usecase.SubmitReportUseCase
import kotlinx.coroutines.launch

data class ReportUIState (
    val description: String = "",
    val selectedType: String = "",
    val photoUri: Uri? = null,
    val location: Location = Location(0.0, 0.0)
)

class ReportViewModel(
    private val submitReportUseCase: SubmitReportUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ReportUIState())
        private set

    var isSendingReport by mutableStateOf(false)

    fun updateDescription(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun updateSelectedType(selectedType: String) {
        uiState = uiState.copy(selectedType = selectedType)
    }

    fun updatePhotoUri(uri: Uri?) {
        uiState = uiState.copy(photoUri = uri)
    }

    fun onSendReport(callback: (SendResult) -> Unit) {
        if (isSendingReport)
            return

        val photoUri = uiState.photoUri

        if (photoUri == null) {
            callback(SendResult.Error("Foto não selecionada"))
            return
        }

        viewModelScope.launch {
            isSendingReport = true

            val report = Report(
                description = uiState.description,
                type = uiState.selectedType.toReportType() ?: ReportType.OTHER,
                imageUri = photoUri,
                location = uiState.location
            )

            submitReportUseCase(report).fold(
                onSuccess = {
                    callback(SendResult.Success)
                },
                onFailure = {
                    callback(SendResult.Error(it.message ?: "Erro desconhecido"))
                }
            )

            isSendingReport = false
        }
    }
}

class ReportViewModelFactory(
    private val submitReportUseCase: SubmitReportUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportViewModel(submitReportUseCase) as T
    }
}
