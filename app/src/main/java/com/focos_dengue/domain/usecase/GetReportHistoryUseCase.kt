package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.repository.ReportRepository

class GetReportHistoryUseCase(
    private val repository: ReportRepository
) {

    suspend operator fun invoke(): Result<List<ReportModel>> {
        return repository.getReportHistory()
    }
}