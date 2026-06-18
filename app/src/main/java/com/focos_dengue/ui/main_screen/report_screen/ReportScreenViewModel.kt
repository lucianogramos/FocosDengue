package com.focos_dengue.ui.main_screen.report_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.usecase.GetReportsUseCase
import kotlinx.coroutines.launch

data class ReportUIState(
    val reports: List<ReportModel> = emptyList(),
    val isLoading: Boolean = false,
    val isEndReached: Boolean = false,
)

class ReportScreenViewModel(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ReportUIState())
        private set

    private val currentPage = 0L
    private val pageSize = 10L

    fun loadNextReports() {
        if (uiState.isLoading || uiState.isEndReached)
            return

        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            val newReports = getReportsUseCase(currentPage, pageSize)

            Log.d("console:", newReports.toString())

            uiState = uiState.copy(
                reports = uiState.reports + newReports,
                isLoading = false,
                isEndReached = newReports.isEmpty()
            )
        }
    }
}

class ReportScreenViewModelFactory(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportScreenViewModel(getReportsUseCase) as T
    }
}
