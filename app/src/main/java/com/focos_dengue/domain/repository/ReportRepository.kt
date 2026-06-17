package com.focos_dengue.domain.repository

import com.focos_dengue.domain.model.Report

interface ReportRepository {

    suspend fun submitReport(report: Report): Result<Unit>

    suspend fun getReports(): Result<List<Report>>
}