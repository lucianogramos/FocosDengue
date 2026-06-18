package com.focos_dengue.ui.main_screen.send_report_screen

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.model.AddressModel
import com.focos_dengue.domain.model.LocationModel
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.model.ReportType
import com.focos_dengue.domain.model.toReportType
import com.focos_dengue.domain.usecase.GetAddressFromLatLngUseCase
import com.focos_dengue.domain.usecase.SubmitReportUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

data class SendReportUIState (
    val description: String = "",
    val selectedType: String = "",
    val photoUri: Uri? = null,
    val location: LocationModel = LocationModel(
        0.0, 0.0,
        AddressModel(
            "Selecione um ponto",
            "Selecione um ponto",
            "",
            "Selecione um ponto",
            "Selecione um ponto"
        )
    )
)

class SendReportViewModel(
    private val submitReportUseCase: SubmitReportUseCase,
    private val getAddressFromLatLngUseCase: GetAddressFromLatLngUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SendReportUIState())
        private set
    private var isSendingReport = false
    private var addressJob: Job? = null

    fun updateDescription(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun updateSelectedType(selectedType: String) {
        uiState = uiState.copy(selectedType = selectedType)
    }

    fun updatePhotoUri(uri: Uri?) {
        uiState = uiState.copy(photoUri = uri)
    }

    fun updateLocation(latLng: LatLng) {
        addressJob?.cancel()

        addressJob = viewModelScope.launch {
            val lat = latLng.latitude
            val lng = latLng.longitude
            val address = getAddressFromLatLngUseCase(lat, lng)
            uiState = uiState.copy(location = LocationModel(lat, lng, address))
        }
    }

    fun onSendReport(callback: (SendReportResult) -> Unit) {
        if (isSendingReport)
            return

        val selectedType = uiState.selectedType

        if (selectedType.isBlank()) {
            callback(SendReportResult.Error("Selecione um tipo de denúncia"))
            return
        }

        val photoUri = uiState.photoUri

        if (photoUri == null) {
            callback(SendReportResult.Error("Foto não selecionada"))
            return
        }

        isSendingReport = true

        viewModelScope.launch {
            val report = ReportModel(
                description = uiState.description,
                type = selectedType.toReportType() ?: ReportType.OTHER,
                imageUri = photoUri,
                location = uiState.location
            )

            submitReportUseCase(report).fold(
                onSuccess = {
                    callback(SendReportResult.Success)
                },
                onFailure = {
                    callback(SendReportResult.Error(it.message ?: "Erro desconhecido"))
                }
            )

            isSendingReport = false
        }
    }
}

class SendReportViewModelFactory(
    private val submitReportUseCase: SubmitReportUseCase,
    private val getAddressFromLatLngUseCase: GetAddressFromLatLngUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SendReportViewModel(submitReportUseCase, getAddressFromLatLngUseCase) as T
    }
}
