package com.focos_dengue.ui.main_screen.report_screen

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
import com.focos_dengue.domain.repository.ReportRepository
import kotlinx.coroutines.launch

data class ReportUIState (
    val description: String = "",
    val photoUri: Uri? = null,
    val location: Location = Location(0.0, 0.0)
)

class ReportViewModel(
    private val reportRepository: ReportRepository
) : ViewModel() {
    var uiState by mutableStateOf(ReportUIState())
        private set

    fun updateDescription(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun updatePhotoUri(uri: Uri?) {
        uiState = uiState.copy(photoUri = uri)
    }

    fun onSendReport(callback: (SendResult) -> Unit) {
        viewModelScope.launch {
            val report = Report(
                description = uiState.description,
                type = ReportType.TIRES_DISCARDED,
                imageUrl = uiState.photoUri,
                location = uiState.location
            )
            reportRepository.submitReport(report).fold(
                onSuccess = {
                    callback(SendResult.Success)
                },
                onFailure = {
                    callback(SendResult.Error(it.message ?: "Erro desconhecido"))
                }
            )
        }
    }
}

class ReportViewModelFactory(
    private val reportRepository: ReportRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportViewModel(reportRepository) as T
    }
}
