package com.focos_dengue.domain.repository

import com.focos_dengue.domain.model.ReportModel

interface ReportRepository {

    suspend fun submitReport(report: ReportModel): Result<Unit>

    suspend fun getReportHistory(): Result<List<ReportModel>>
}