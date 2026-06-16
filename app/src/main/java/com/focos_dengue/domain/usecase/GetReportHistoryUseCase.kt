package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.repository.ReportRepository

class GetReportHistoryUseCase(
    private val repository: ReportRepository
) {

    suspend operator fun invoke(): Result<List<Report>> {
        return repository.getReportHistory()
    }
}