package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.focos_dengue.domain.usecase.GetReportsUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

data class ReportUIModel(
    val description: String,
    val imageUri: String,
    val address: String,
    val createdAt: String
)

data class ReportUIState(
    val reports: List<ReportUIModel> = emptyList(),
    val isLoading: Boolean = false,
    val isEndReached: Boolean = false,
)

class ReportScreenViewModel(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ReportUIState())
        private set

    private var currentPage = 0L
    private val pageSize = 5L

    @OptIn(ExperimentalTime::class)
    fun loadNextReports() {
        if (uiState.isLoading || uiState.isEndReached)
            return

        uiState = uiState.copy(isLoading = true)

        val formatter = LocalDateTime.Format {
            day(); char('/'); monthNumber(); char('/'); year()
            char(' '); char('à'); char('s'); char(' ')
            hour(); char(':'); minute()
        }

        viewModelScope.launch {
            val newReports = getReportsUseCase(currentPage * pageSize, pageSize).map {
                ReportUIModel(
                    description = it.description ?: "",
                    imageUri = it.imageUri.toString(),
                    address = it.location.address.toString(),
                    createdAt =
                        if (it.createdAt != null) {
                            it.createdAt.toLocalDateTime(TimeZone.currentSystemDefault())
                                .format(formatter)
                        }
                        else {
                            "Não foi possível obter a data"
                        }
                )
            }

            if (newReports.isNotEmpty())
                currentPage++

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
